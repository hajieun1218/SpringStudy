package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListController {
	
	@RequestMapping("main/list.do")
	public String main_list(HttpServletRequest request) {
		request.setAttribute("msg", "Hello Spring!!");
		return "list"; // jsp의 이름만 설정 (확장자 X)
	}
	
	/*		
	 * 		public class Model {
	  			public void addAttribute(String key,Object obj) { 
	  				request.setAttribute(key, obj);
	  			}
	  		}
	 */
	// model을 사용하면  request.getparameter를 model이 해준다(vo에 값을 채워줌)
	// model : 값 받는 역할이 아니라 데이터를 추가해서 넘겨주는 역할
	@RequestMapping("main/list2.do")
	public String main_list2(Model model) {
		model.addAttribute("msg", "Hello Spring~~"); // model은 JSP에 추가된 데이터만 전송
		return "list2"; // view
	}
	
	@GetMapping("main/input.do")
	public String main_input() {
		return "input";
	}
	
	@PostMapping("main/output.do")
	public String main_output(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String addr=request.getParameter("addr");
		String tel=request.getParameter("tel");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setName(name);
		vo.setSex(sex);
		vo.setAddr(addr);
		vo.setTel(tel);
		vo.setContent(content);
		
		request.setAttribute("vo", vo);
		return "output";
	}
	
	@PostMapping("main/output2.do")
	public String main_output2(Model model, MemberVO vo) {
		model.addAttribute("vo", vo);  // 넘어오는 변수명과 vo변수명이 일치해야 값을 채울 수 있음
		return "output";
	}
}
