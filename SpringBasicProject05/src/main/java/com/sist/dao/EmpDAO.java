package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
	    <!-- SqlSessionFactory => DAO�� ���� -->
		<!-- sqlSessionFactory�� DAO���� ��ӹ��� SqlSessionDaoSupport�� ������ �ִ� -->
		<bean id="dao" class="com.sist.dao.EmpDAO"
			p:sqlSessionFactory-ref="ssf"
		/>
 */

// ID ���ָ� �ڵ� ID ���� => empDAO (Ŭ���� �̸��� ù�ڸ� �ҹ���)
// <bean id="eDao" class="com.sist.dao.EmpDAO"/>
@Repository // �޸��Ҵ�(DAO)
public class EmpDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<EmpVO> empAllData() {
		return getSqlSession().selectList("empAllData");
		// getSqlSession() -> openSession(),session.close() ����
		// insert ����ϸ� commit ���ԵǾ�����
	}
	
	public EmpVO empFindData(int empno) {
		return getSqlSession().selectOne("empFindData", empno);
	}
}
