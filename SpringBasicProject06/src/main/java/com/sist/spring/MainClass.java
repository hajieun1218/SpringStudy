package com.sist.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.SystemPropertyUtils;

import com.sist.dao.*;
/*
 		Ŭ���� �޸� �Ҵ�
 		@Component, @Repository, @Service, @Controller, @RestController, @ControllerAdvice, 
 		
 		���� (DI)
  		@Required, @Autowired, @PostConstruct, @PreDestroy, @Resource
 */
@Component
public class MainClass {

	// main������ ����ϸ� getBean�ᵵ ������ ������ �޼ҵ尡 �ִٸ� ���������� �޾Ƽ� ���°� ���ϴ�
	@Autowired
	private EmpDAO dao;
	
	// init-method
	@PostConstruct
	public void init() {
		System.out.println("============= ������� =============");
	}
	
	// destory-method
	@PreDestroy
	public void destory() {
		System.out.println("============= ���α׷� ���� =============");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// �޸� ���� ����Ϸ���  GenericXmlApplicationContext ���
		GenericXmlApplicationContext app=new GenericXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		System.out.println(mc.dao);
		
		EmpVO vo=mc.dao.empFindData(7788);
		System.out.println("��� : "+vo.getEmpno());
		System.out.println("�̸� : "+vo.getEname());
		System.out.println("���� : "+vo.getJob());
		System.out.println("�Ի��� : "+vo.getHiredate());
		System.out.println("�޿� : "+vo.getSal());
		
		app.close(); // �޸� ����
	}

}
