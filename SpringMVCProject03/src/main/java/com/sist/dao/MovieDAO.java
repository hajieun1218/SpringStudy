package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieDAO {
	@Autowired
	private MovieMapper mapper; 
	// mapper를 직접 사용하면 안되고 구현된 내용을 사용해야함
	// 구현은 application-datasource.xml  MapperFactoryBean에서 구현
	// dao에서는 구현된 mapper를 받는다
	
	// 현재상영영화
	public List<MovieVO> movieListData(Map map) {
		return mapper.movieListData(map);
	}
	
	// 총페이지
	public int movieTotalPage(int type) {
		return mapper.movieTotalPage(type);
	}
	
	// 상세보기
	public MovieVO movieDetailData(int mno) {
		return mapper.movieDetailData(mno);
	}
}
