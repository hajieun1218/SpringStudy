package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empAllData() {
		return mapper.empAllData();
	}
	
	public List<Integer> empGetMgr(){
		return mapper.empGetMgr();
	}
	
	public List<String> empGetJob(){
		return mapper.empGetJob();
	}
	
	public void empInsert(EmpVO vo){
		mapper.empInsert(vo);
	}
}
