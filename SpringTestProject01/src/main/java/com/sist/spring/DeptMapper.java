package com.sist.spring;

import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface DeptMapper {
	
	@Select("SELECT * FROM dept")
	public List<DeptVO> deptAllData();
}
