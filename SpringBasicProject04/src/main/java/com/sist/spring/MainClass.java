package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import java.util.*;

// 클라이언트 영역
public class MainClass {

	public static void main(String[] args) {
		
		// 1. XML 파일 읽기
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getJob());
		}
	}

}
