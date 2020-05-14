package com.sist.databoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("board/")
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@RequestMapping("update_ok.do")
	public String board_update_ok(DataBoardVO vo) {
		String result="";
		
		// 비밀번호 체크
		String db_pwd=dao.databoardGetPassword(vo.getNo());
		if(vo.getPwd().equals(db_pwd)) {
			result="OK";
			dao.databoardUpdate(vo);
		}
		else {
			result="NOPWD";
		}
		
		return result;
	}
	
	@RequestMapping("delete_ok.do")
	public String board_delete_ok(int no, String pwd) {
		String result="";
		
		DataBoardVO vo=dao.databoardFileInfoData(no);
		boolean bCheck=dao.databoardDelete(no, pwd);
		
		if(bCheck==true) {
			result="OK";
			try {
				// 파일제거 시작
				// DB 먼저 지우고 파일 지우기
				if(vo.getFilecount()>0) {
					StringTokenizer st=new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens()) {
						File file=new File("c:\\upload\\"+st.nextToken());
						file.delete();
					}
				}
				// 그래프 png파일 지우기
				File file=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringMVCProject04\\board\\"+no+".png");
				file.delete();
			} catch(Exception ex) {}
		}
		else {
			result="NOPWD";
		}
		
		return result;
	}
}
