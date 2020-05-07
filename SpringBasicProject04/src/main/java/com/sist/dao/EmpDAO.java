package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;

public class EmpDAO extends SqlSessionDaoSupport{
	
	public List<EmpVO> empAllData() {
		return getSqlSession().selectList("empAllData");
		// getSqlSession() -> openSession(),session.close() ����
		// insert ����ϸ� commit ���ԵǾ�����
	}
	
	public EmpVO empFindData(int empno) {
		return getSqlSession().selectOne("empFindData", empno);
	}
}
