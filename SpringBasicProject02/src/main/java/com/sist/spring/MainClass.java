package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		// Main�� ������ ApplicationContext, Web�̸� WebApplicationContext
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		SawonContainer sc=(SawonContainer)app.getBean("sc"); // DL
		sc.display();
		
		/*
		  		class ApplicationContext {
		  			private Map map=new HashMap();
		  			public ApplicationContext() {
		  				==> XML�� �Ľ�
		  				map.put("a",Class.forName("A"));
		  				map.put("b",Class.forName("B"));
		  				map.put("c",Class.forName("C"));
		  				map.put("d",Class.forName("D"));
		  			}
		  			public Object getBean(String id) {
		  				return map.get(id);
		  			}
		  			
		  			A a=(A)app.getBean("a");
		  		}
		 */
		
	}

}
