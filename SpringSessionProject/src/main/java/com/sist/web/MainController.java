package com.sist.web;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

@Controller
public class MainController {
	@Autowired
	private MusicDAO dao;
	
	@RequestMapping("main/list.do")
	public String main_list() {
		return "main/list";
	}
	
	// React�� Ajax�� ����ϸ� MainController������ ȭ�鸸 �̵��ϸ� �ȴ�
	@RequestMapping("main/detail.do")
	public String main_detail(Model model, int mno) {
		
		MusicVO vo=dao.musicDetailData(mno);
		JSONObject obj=new JSONObject(); // {}
		obj.put("mno", vo.getMno());
		obj.put("title", vo.getTitle());
		obj.put("singer", vo.getSinger());
		obj.put("album", vo.getAlbum());
		obj.put("state", vo.getState());
		obj.put("idcrement", vo.getIdcrement());
		obj.put("poster", vo.getPoster());
		obj.put("key", vo.getKey());
		
		model.addAttribute("json", obj.toJSONString());
		
		model.addAttribute("mno", mno);
		System.out.println(mno);
		System.out.println(obj.toJSONString());
		return "main/detail";
	}
	
	@GetMapping("main/login.do")
	public String main_login() {
		return "main/login";
	}
	
	/*
	 * 	  ���尴ü
	 * 		request,response,session,pageContext,page,exception,config,out => DispatcherServlet�� ����������
	 */
	// session�� �Ű������� �޴´�
	// cookie�� new�� �����ϰ� response.addCookie(cook) �ϱ⶧���� response�� �Ű������� �޴´�
	// �ٿ�ε� => response �Ű������� �޴´�
	@PostMapping("main/login_ok.do")
	public String main_login_ok(String id, String pwd, Model model, HttpSession session) {
		String result="";
		int count=dao.idCount(id);
		if(count==0) { // ID�� ���� ���
			result="NOID";
		}
		else { // ID�� �����ϴ� ���
			String db_pwd=dao.memberGetPassword(id);
			if(db_pwd.equals(pwd)) {
				result="OK";
				session.setAttribute("id", id);
			}
			else {
				result="NOPWD";
			}
		}
		model.addAttribute("result", result);
		return "main/login_ok";
	}
	
	@RequestMapping("main/logout.do")
	public String main_logout(HttpSession session) {
		session.invalidate(); // session����
		return "redirect:list.do";
	}
}