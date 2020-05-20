package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("main/list.do")
	public String main_list() {
		return "main/list";
	}
	
	@RequestMapping("main/detail.do")
	public String main_detail() {
		return "main/detail";
	}
}
