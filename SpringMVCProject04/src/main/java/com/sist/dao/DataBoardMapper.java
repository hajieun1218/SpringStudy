package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataBoardMapper {
	// 목록
	@Select("SELECT no,subject,name,regdate,hit,num "
		   +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		   +"FROM (SELECT no,subject,name,regdate,hit "
		   +"FROM spring_databoard ORDER BY no DESC)) "
		   +"WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	public int databoardTotalPage();
	
	// INSERT
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
	@Insert("INSERT INTO spring_databoard VALUES("
		   +"#{no},#{name},#{subject},#{content},#{pwd},"
		   +"SYSDATE,0,#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	// 조회수 증가
	@Update("UPDATE spring_databoard SET "
		   +"hit=hit+1 "
		   +"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	// 상세보기
	@Select("SELECT no,name,subject,content,regdate,hit,filename,filesize,filecount "
		   +"FROM spring_databoard "
		   +"WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
}
