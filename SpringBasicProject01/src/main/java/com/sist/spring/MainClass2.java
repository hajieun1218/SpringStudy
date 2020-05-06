package com.sist.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass2 {

	private EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}


	public static void main(String[] args) {

		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		// DL
		// dao라는 이름을 가진 클래스를 가져옴
		//EmpDAO dao=(EmpDAO)app.getBean("dao");
		MainClass2 mc=(MainClass2)app.getBean("mc2");
		
		List<EmpVO> list=mc.dao.empAllData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate()+" "
					+vo.getSal());
		}
	}

}
