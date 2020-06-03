package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.CategoryVO;
import com.sist.dao.FoodService;
import java.util.*;

@RestController
@CrossOrigin(origins="http://localhost:3000") 
// 포트가 다를 때 연결 허용 (cross domain) 
public class JSONController {
	@Autowired
	private FoodService service;
	
	@RequestMapping("category.do")
	public String category() {
		List<CategoryVO> list=service.categoryListData();
		JSONArray arr=new JSONArray();
		for(CategoryVO vo:list) {
			JSONObject obj=new JSONObject();
			obj.put("title", vo.getTitle());
			obj.put("subject", vo.getSubject());
			arr.add(obj);
		}
		return arr.toJSONString();
	}
}
