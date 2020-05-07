package com.sist.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

/*
 * 	@Component  : 일반클래스(ex.MainClass)
 * 	@Repository : DAO
 * 	@Service    : BI -> DAO+DAO(DAO 여러개를 묶어서 사용할 때) ===> Service vs DAO
 * 	@Controller : Model(JSP 파일명 또는 redirect 넘어감)
 * 	@RestController : Restful -> 필요한 데이터 전송 (AJAX) , XML/JSON
 * 	@ControllerAdvice : AOP
 */

/*
 * 	<!-- 데이터베이스 정보 전송 -->
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
@Component("ds") // 메모리할당(일반클래스)
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
