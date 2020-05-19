package com.sist.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		// ����� ���� Ŭ������ ������ �����̳� => ApplicationContext
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.dao.emp_select();
		mc.dao.emp_delete(1);
		mc.dao.emp_insert("ȫ�浿");
		mc.dao.emp_update(1, "ȫ�浿");
		mc.dao.display();
	}
}
