package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;

	@GetMapping("emp/list.do")
	public String emp_list(Model model){
		List<EmpVO> list=dao.empAllData();
		model.addAttribute("list",list);
		return "emp/list";
	}
	
	@GetMapping("emp/insert.do")
	public String emp_insert(Model model) {
		List<Integer> mgrs=dao.empGetMgr();
		List<String> jobs=dao.empGetJob();
		model.addAttribute("mgrs",mgrs);
		model.addAttribute("jobs",jobs);
		return "emp/insert";
	}
	
	@PostMapping("emp/insert_ok.do")
	public String emp_insert_ok(EmpVO vo) { // EmpVO vo : ÄÚ¸àµå °´Ã¼
		dao.empInsert(vo);
		return "redirect:list.do";
	}
}
