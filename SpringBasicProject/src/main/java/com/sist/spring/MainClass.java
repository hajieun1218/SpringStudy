package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// XML �Ľ�
		// ApplicationContext : Ŭ����������
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		// ��ϵ� Ŭ������ ���
		// ����ϰ��� Spring�� �ڵ����� �޸� ȸ��
		Sawon sa=(Sawon)app.getBean("sa5"); // DL(Ŭ���� ��ü ã��)
		System.out.println("sa="+sa); 
		System.out.println("��� : "+sa.getSabun());
		System.out.println("�̸� : "+sa.getName());
		System.out.println("�μ� : "+sa.getDept());
		//sa.display();
		//sa.setSabun(1);
		//sa.setName("ȫ�浿");
		//sa.setDept("���ߺ�");
		
		//Sawon sa1=(Sawon)app.getBean("sa");
		//System.out.println("sa1="+sa1); // ������ 2���� �Ǿ��µ� �ּҰ� ���� -> �̱���
		//sa1.display();
		//sa1.setSabun(2);
		//sa1.setName("��û��");
		//sa1.setDept("������");
		
		//System.out.println(sa.getName());
		//System.out.println(sa1.getName());
	}

}
