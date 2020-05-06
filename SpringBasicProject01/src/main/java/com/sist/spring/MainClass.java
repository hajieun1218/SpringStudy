package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;

// DL => ��ϵ� Ŭ������ �о�� ��
public class MainClass {

	private EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}


	public static void main(String[] args) {

		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		// DL
		// dao��� �̸��� ���� Ŭ������ ������
		//EmpDAO dao=(EmpDAO)app.getBean("dao");
		MainClass mc=(MainClass)app.getBean("mc");
		
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
