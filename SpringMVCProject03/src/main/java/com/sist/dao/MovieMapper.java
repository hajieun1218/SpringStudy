package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface MovieMapper {
	
	// 현재상영영화
	@Select("SELECT mno,title,poster,num "
		   +"FROM (SELECT mno,title,poster,rownum as num "
		   +"FROM (SELECT mno,title,poster "
		   +"FROM movie WHERE type=#{type} )) "
		   +"WHERE num BETWEEN #{start} AND #{end}")
	public List<MovieVO> movieListData(Map map);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM movie "
		   +"WHERE type=#{type}")
	public int movieTotalPage(int type);
	
	// 상세보기
	@Select("SELECT * FROM movie "
		   +"WHERE mno=#{mno}")
	public MovieVO movieDetailData(int mno);
}
