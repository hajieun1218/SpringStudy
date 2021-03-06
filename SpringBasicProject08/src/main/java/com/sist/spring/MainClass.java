package com.sist.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import java.util.*;

@Component
public class MainClass {
	@Autowired
	private MemberDAO dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<MemberVO> list=mc.dao.memberAllData();
		for(MemberVO vo:list) {
			System.out.println(vo.getNo()+" "+vo.getName()+" "+vo.getAddr()+" "+vo.getTel()+" "+vo.getSex());
		}
		
	}

}
