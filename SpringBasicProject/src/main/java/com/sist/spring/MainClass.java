package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// XML 파싱
		// ApplicationContext : 클래스관리자
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		// 등록된 클래스를 사용
		// 사용하고나면 Spring이 자동으로 메모리 회수
		Sawon sa=(Sawon)app.getBean("sa5"); // DL(클래스 객체 찾기)
		System.out.println("sa="+sa); 
		System.out.println("사번 : "+sa.getSabun());
		System.out.println("이름 : "+sa.getName());
		System.out.println("부서 : "+sa.getDept());
		//sa.display();
		//sa.setSabun(1);
		//sa.setName("홍길동");
		//sa.setDept("개발부");
		
		//Sawon sa1=(Sawon)app.getBean("sa");
		//System.out.println("sa1="+sa1); // 생성이 2개가 되었는데 주소가 같다 -> 싱글턴
		//sa1.display();
		//sa1.setSabun(2);
		//sa1.setName("심청이");
		//sa1.setDept("영업부");
		
		//System.out.println(sa.getName());
		//System.out.println(sa1.getName());
	}

}
