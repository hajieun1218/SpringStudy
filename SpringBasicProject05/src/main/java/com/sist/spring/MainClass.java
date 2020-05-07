package com.sist.spring;

import java.util.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
	
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		/*EmpDAO dao=(EmpDAO)app.getBean("empDAO");
		List<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}*/
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<EmpVO> list=mc.dao.empAllData();
		
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}
	}

}
