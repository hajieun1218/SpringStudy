package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FoodService {
	@Autowired
	private CategoryDAO cdao;
	@Autowired
	private FoodDAO fdao;
	
	public List<CategoryVO> categoryListData() {
		return cdao.categoryListData();
	}
	
}
