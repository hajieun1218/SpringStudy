package com.sist.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/*
 * 		스프링이 하는 역할
 * 		1. 클래스 메모리 할당 (클래스 관리자)
 * 		2. 멤버변수에 값을 주입
 * 		3. 클래스의 메모리 해제
 * 		4. 반복적인 코딩을 제거 => 소스 간결화, 가독성, 공통성 
 */

/*		
 * 		@Autowired => 자동주입(클래스의 메모리 주소만 주입) => 스프링에 등록된 클래스 사용이 가능
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
 * <!-- SqlSessionFactory 생성 -->
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds" 
		p:configLocation="classpath:Config.xml"
	/>
 */
@Component("ssf")
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {

	/*@Autowired // 자동으로 생성된 객체의 주소를 넣어줌 (자동주입) - 아이디 모를때 
	@Qualifier("ds")*/
	//@Resource(name="ds")
	@Autowired // 중복되는 클래스가 없을때는 Autowired가 편하다
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
