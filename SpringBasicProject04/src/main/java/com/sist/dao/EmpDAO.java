package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;

public class EmpDAO extends SqlSessionDaoSupport{
	
	public List<EmpVO> empAllData() {
		return getSqlSession().selectList("empAllData");
		// getSqlSession() -> openSession(),session.close() 포함
		// insert 사용하면 commit 포함되어있음
	}
	
	public EmpVO empFindData(int empno) {
		return getSqlSession().selectOne("empFindData", empno);
	}
}
