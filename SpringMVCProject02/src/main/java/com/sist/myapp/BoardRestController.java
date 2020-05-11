package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;

// 스크립트 넘겨줄 때 
// 리턴값이 파일명(.jsp,.do)이 아니라 일반 문자열 ==> update_ok.jsp 사용 X
// ajax, react에서 많이 사용 => 필요한 데이터 (XML,JSON)을 넘겨줌
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck) { // 비밀번호 일치
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\"</script>";
		}
		else { // 비밀번호 틀림
			result="<script>alert(\"Password Fail\"); history.back();</script>";
		}
		return result;
	}
	
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no, String pwd) {
		String result="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck) { // 비밀번호 일치
			result="<script>location.href=\"list.do\"</script>";
		}
		else { // 비밀번호 틀림
			result="<script>alert(\"Password Fail\"); history.back();</script>";
		}
		return result;
	}
	
}
