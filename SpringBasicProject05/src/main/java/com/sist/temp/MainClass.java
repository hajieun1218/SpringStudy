package com.sist.temp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

// Spring ���������ȿ� ���� ���� �޸� �Ҵ�
// �޸� �Ҵ��� �� �Ŀ� ������ �ؾ��Ѵ�
@Component
public class MainClass {

	/*@Autowired // �ڵ�����, �޸��Ҵ�
	@Qualifier("boardDAO") // Ư����ü ���� */	
	@Resource(name="boardDAO")
	private MyDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass"); // �������� ��ϵ� Ŭ������ ����ؾ��� (new X)
		mc.dao.select();
		// @Autowired�� ����ϸ� �ڵ������� �ϴٺ��ϱ� boardDAO���� noticeDAO���� ���� error 
		// --> Qualifier("boardDAO")�� ���� �������
		// @Autowired, @Qualifier("boardDAO") �ΰ� ��ģ���� @Resource(name="boardDAO")
	}

}
