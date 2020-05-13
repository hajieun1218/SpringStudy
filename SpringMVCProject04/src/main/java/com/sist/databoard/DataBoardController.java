package com.sist.databoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;

@Controller
@RequestMapping("board/")
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@RequestMapping("list.do")
	public String board_list(Model model, String page) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=dao.databoardListData(map);
		int totalpage=dao.databoardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "board/list";
	}
	
	@RequestMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(DataBoardVO vo) throws Exception {
		List<MultipartFile> list=vo.getFiles();
		String temp1="";
		String temp2="";
		
		// 사용자가 파일 올렸다면
		if(list!=null && list.size()>0) { 
			// mf : 파일 하나
			for(MultipartFile mf:list) {
				String fn=mf.getOriginalFilename();
				File file=new File("c:\\upload\\"+fn);
				mf.transferTo(file); // 데이터 이동 (upload)
				
				temp1+=fn+",";
				temp2+=file.length()+",";
			}
			
			temp1=temp1.substring(0,temp1.lastIndexOf(","));
			temp2=temp2.substring(0,temp2.lastIndexOf(","));
			
			vo.setFilename(temp1);
			vo.setFilesize(temp2);
			vo.setFilecount(list.size());
		}
		// 파일이 없는 경우
		else {
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		
		dao.databoardInsert(vo);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("detail.do")
	public String board_detail(Model model, int no) {
		DataBoardVO vo=dao.databoardDetailData(no);
		
		if(vo.getFilecount()>0) {
			StringTokenizer st1=new StringTokenizer(vo.getFilename(),",");
			List<String> fList=new ArrayList<String>();
			while(st1.hasMoreTokens()) {
				fList.add(st1.nextToken());
			}
			
			StringTokenizer st2=new StringTokenizer(vo.getFilesize(),",");
			List<String> sList=new ArrayList<String>();
			while(st2.hasMoreTokens()) {
				sList.add(st2.nextToken());
			}
			
			model.addAttribute("fList", fList);
			model.addAttribute("sList", sList);
		}
		
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	// 다운로드는 화면이동 X -> 리턴형 void
	@RequestMapping("download.do")
	public void board_download(String fn, HttpServletResponse response) {
		try {
			File file=new File("c:\\upload\\"+fn);
			// 다운로드 하기전에 먼저 보내는 내용 : Header
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			// 서버 => c:\\upload\\a.jpg
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 클라이언트 영역 (서버에서 클라이언트로 복사)
			// response.getOutputStream() : 클라이언트의 저장장소
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			
			int i=0; // 읽은 byte개수
			byte[] buffer=new byte[1024]; // 1024btye씩 다운로드
			
			while((i=bis.read(buffer,0,1024))!=-1) { // -1: EOF
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
		} catch(Exception ex) {}
	}
}
