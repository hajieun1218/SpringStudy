package com.sist.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

/*
 * 	@Component  : �Ϲ�Ŭ����(ex.MainClass)
 * 	@Repository : DAO
 * 	@Service    : BI -> DAO+DAO(DAO �������� ��� ����� ��) ===> Service vs DAO
 * 	@Controller : Model(JSP ���ϸ� �Ǵ� redirect �Ѿ)
 * 	@RestController : Restful -> �ʿ��� ������ ���� (AJAX) , XML/JSON
 * 	@ControllerAdvice : AOP
 */

/*
 * 	<!-- �����ͺ��̽� ���� ���� -->
 * 	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@localhost:1521:XE"
		p:username="hr"
		p:password="happy"
		p:maxActive="20"
		p:maxIdle="10"
		p:maxWait="-1"
	/>
 */
@Component("ds") // �޸��Ҵ�(�Ϲ�Ŭ����)
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource() {
		// setter DI
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		setUsername("hr");
		setPassword("happy");
		setMaxActive(20);
		setMaxIdle(10);
		setMaxWait(-1);
	}
	
}
