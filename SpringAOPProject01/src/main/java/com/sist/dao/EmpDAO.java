package com.sist.dao;

import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	public void emp_select() {
//		getConnection();
		System.out.println("EMP ���̺��� �����͸� ������ �´�");
//		disConnection();
	}
	
	public void emp_insert(String name) {
//		getConnection();
		System.out.println(name+"��(��) �߰�");
//		disConnection();
	}
	
	public void emp_update(int sabun, String name) {
//		getConnection();
		//int a=10/0;
		System.out.println(sabun+"�� �ش�Ǵ� "+name+"�� �����Ͽ����ϴ�");
//		disConnection();
	}
	
	public String emp_delete(int sabun) {
//		getConnection();
		System.out.println(sabun+"��(��) �����Ͽ����ϴ�");
//		disConnection();
		
		return "�����Ϸ�";
	}
	
	public void driverClassNameConfig() {
		System.out.println("����Ŭ ����̹� ���!!");
	}
	
	public void display() {
		for(int i=0;i<=1000000000;i++) {
			
		}
		System.out.println("for�� ����...");
	}
}
