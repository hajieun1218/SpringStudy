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
		
		// ����ڰ� ���� �÷ȴٸ�
		if(list!=null && list.size()>0) { 
			// mf : ���� �ϳ�
			for(MultipartFile mf:list) {
				String fn=mf.getOriginalFilename();
				File file=new File("c:\\upload\\"+fn);
				mf.transferTo(file); // ������ �̵� (upload)
				
				temp1+=fn+",";
				temp2+=file.length()+",";
			}
			
			temp1=temp1.substring(0,temp1.lastIndexOf(","));
			temp2=temp2.substring(0,temp2.lastIndexOf(","));
			
			vo.setFilename(temp1);
			vo.setFilesize(temp2);
			vo.setFilecount(list.size());
		}
		// ������ ���� ���
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
	
	// �ٿ�ε�� ȭ���̵� X -> ������ void
	@RequestMapping("download.do")
	public void board_download(String fn, HttpServletResponse response) {
		try {
			File file=new File("c:\\upload\\"+fn);
			// �ٿ�ε� �ϱ����� ���� ������ ���� : Header
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			// ���� => c:\\upload\\a.jpg
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// Ŭ���̾�Ʈ ���� (�������� Ŭ���̾�Ʈ�� ����)
			// response.getOutputStream() : Ŭ���̾�Ʈ�� �������
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			
			int i=0; // ���� byte����
			byte[] buffer=new byte[1024]; // 1024btye�� �ٿ�ε�
			
			while((i=bis.read(buffer,0,1024))!=-1) { // -1: EOF
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
		} catch(Exception ex) {}
	}
}
