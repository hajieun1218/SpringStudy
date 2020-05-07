package com.sist.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/*
 * 		�������� �ϴ� ����
 * 		1. Ŭ���� �޸� �Ҵ� (Ŭ���� ������)
 * 		2. ��������� ���� ����
 * 		3. Ŭ������ �޸� ����
 * 		4. �ݺ����� �ڵ��� ���� => �ҽ� ����ȭ, ������, ���뼺 
 */

/*		
 * 		@Autowired => �ڵ�����(Ŭ������ �޸� �ּҸ� ����) => �������� ��ϵ� Ŭ���� ����� ����
  		public class A {
			@
			MyDAO dao; => FIELD
			
			@
			public A(MyDAO dao) {} => CONSTRUCTOR
			
			@
			public void setMyDAO(MyDAO dao){} => PARAMETER
			
			@
			public void display(MYDAO dao){} 
		}
 */

/*
 * <!-- SqlSessionFactory ���� -->
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds" 
		p:configLocation="classpath:Config.xml"
	/>
 */
@Component("ssf")
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {

	/*@Autowired // �ڵ����� ������ ��ü�� �ּҸ� �־��� (�ڵ�����) - ���̵� �𸦶� 
	@Qualifier("ds")*/
	//@Resource(name="ds")
	@Autowired // �ߺ��Ǵ� Ŭ������ �������� Autowired�� ���ϴ�
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	public MySqlSessionFactoryBean() {
		try {
			org.springframework.core.io.Resource res=new ClassPathResource("Config.xml");
			setConfigLocation(res);
		} catch(Exception ex) {}
	}
	
}
