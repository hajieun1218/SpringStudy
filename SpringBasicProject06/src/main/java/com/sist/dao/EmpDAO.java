package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmpDAO extends SqlSessionDaoSupport{
	
	// setter에 값을 채운다
	// app.xml의 ssf객체를 sqlSessionFactory에 채운다
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
