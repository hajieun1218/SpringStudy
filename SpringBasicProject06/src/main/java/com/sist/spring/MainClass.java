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
 		클래스 메모리 할당
 		@Component, @Repository, @Service, @Controller, @RestController, @ControllerAdvice, 
 		
 		주입 (DI)
  		@Required, @Autowired, @PostConstruct, @PreDestroy, @Resource
 */
@Component
public class MainClass {

	// main에서만 사용하면 getBean써도 되지만 여러개 메소드가 있다면 전역변수로 받아서 쓰는게 편하다
	@Autowired
	private EmpDAO dao;
	
	// init-method
	@PostConstruct
	public void init() {
		System.out.println("============= 사원정보 =============");
	}
	
	// destory-method
	@PreDestroy
	public void destory() {
		System.out.println("============= 프로그램 종료 =============");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 메모리 해제 사용하려면  GenericXmlApplicationContext 사용
		GenericXmlApplicationContext app=new GenericXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		System.out.println(mc.dao);
		
		EmpVO vo=mc.dao.empFindData(7788);
		System.out.println("사번 : "+vo.getEmpno());
		System.out.println("이름 : "+vo.getEname());
		System.out.println("직위 : "+vo.getJob());
		System.out.println("입사일 : "+vo.getHiredate());
		System.out.println("급여 : "+vo.getSal());
		
		app.close(); // 메모리 해제
	}

}
