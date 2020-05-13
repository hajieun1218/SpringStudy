package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	// 목록
	public List<DataBoardVO> databoardListData(Map map) {
		return mapper.databoardListData(map);
	}
	
	// 총페이지
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	// Insert
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	// 상세보기
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
}
