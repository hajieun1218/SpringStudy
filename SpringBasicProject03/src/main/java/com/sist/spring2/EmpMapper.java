package com.sist.spring2;

import org.apache.ibatis.annotations.Select;
import java.util.*;

// Mybatis xml 대신 어노테이션 사용
public interface EmpMapper {
	@Select("SELECT * FROM emp")
	public List<EmpVO> empAllData();
	// <select id="empAllData" resultType="EmpVO">SELECT * FROM emp</select>
}
