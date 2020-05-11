package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;

// ��ũ��Ʈ �Ѱ��� �� 
// ���ϰ��� ���ϸ�(.jsp,.do)�� �ƴ϶� �Ϲ� ���ڿ� ==> update_ok.jsp ��� X
// ajax, react���� ���� ��� => �ʿ��� ������ (XML,JSON)�� �Ѱ���
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck) { // ��й�ȣ ��ġ
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\"</script>";
		}
		else { // ��й�ȣ Ʋ��
			result="<script>alert(\"Password Fail\"); history.back();</script>";
		}
		return result;
	}
	
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no, String pwd) {
		String result="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck) { // ��й�ȣ ��ġ
			result="<script>location.href=\"list.do\"</script>";
		}
		else { // ��й�ȣ Ʋ��
			result="<script>alert(\"Password Fail\"); history.back();</script>";
		}
		return result;
	}
	
}
