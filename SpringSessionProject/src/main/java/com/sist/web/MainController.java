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
	
	// React나 Ajax를 사용하면 MainController에서는 화면만 이동하면 된다
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
	 * 	  내장객체
	 * 		request,response,session,pageContext,page,exception,config,out => DispatcherServlet이 가지고있음
	 */
	// session은 매개변수로 받는다
	// cookie는 new로 생성하고 response.addCookie(cook) 하기때문에 response를 매개변수로 받는다
	// 다운로드 => response 매개변수로 받는다
	@PostMapping("main/login_ok.do")
	public String main_login_ok(String id, String pwd, Model model, HttpSession session) {
		String result="";
		int count=dao.idCount(id);
		if(count==0) { // ID가 없는 경우
			result="NOID";
		}
		else { // ID가 존재하는 경우
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
		session.invalidate(); // session해제
		return "redirect:list.do";
	}
}
