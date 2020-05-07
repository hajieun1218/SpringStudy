package com.sist.temp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

// Spring 관리영역안에 들어가기 위해 메모리 할당
// 메모리 할당을 한 후에 주입을 해야한다
@Component
public class MainClass {

	/*@Autowired // 자동주입, 메모리할당
	@Qualifier("boardDAO") // 특정객체 지정 */	
	@Resource(name="boardDAO")
	private MyDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass"); // 스프링에 등록된 클래스를 사용해야함 (new X)
		mc.dao.select();
		// @Autowired만 사용하면 자동주입을 하다보니까 boardDAO인지 noticeDAO인지 몰라서 error 
		// --> Qualifier("boardDAO")를 같이 써줘야함
		// @Autowired, @Qualifier("boardDAO") 두개 합친것이 @Resource(name="boardDAO")
	}

}
