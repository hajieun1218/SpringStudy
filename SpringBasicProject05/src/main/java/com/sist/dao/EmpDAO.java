package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
	    <!-- SqlSessionFactory => DAO에 전송 -->
		<!-- sqlSessionFactory는 DAO에서 상속받은 SqlSessionDaoSupport가 가지고 있다 -->
		<bean id="dao" class="com.sist.dao.EmpDAO"
			p:sqlSessionFactory-ref="ssf"
		/>
 */

// ID 안주면 자동 ID 생성 => empDAO (클래스 이름의 첫자만 소문자)
// <bean id="eDao" class="com.sist.dao.EmpDAO"/>
@Repository // 메모리할당(DAO)
public class EmpDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<EmpVO> empAllData() {
		return getSqlSession().selectList("empAllData");
		// getSqlSession() -> openSession(),session.close() 포함
		// insert 사용하면 commit 포함되어있음
	}
	
	public EmpVO empFindData(int empno) {
		return getSqlSession().selectOne("empFindData", empno);
	}
}
