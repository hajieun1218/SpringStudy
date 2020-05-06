package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		
		// 컨테이너에 XML 파일 전송 => 파싱 => 등록된 클래스의 메모리 할당
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		/*
		 * 		클래스 메모리 할당
		 		setXxx()에 값을 채운다
		 		init-method가 존재하면 호출
		 		============================
		 		 프로그래머가 필요한 메소드를 호출해서 사용
		 		============================
		 		distory-method 를 호출
		 		메모리를 해제
		 */
		
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getSal()+" "+vo.getJob());
		}
	}

}
