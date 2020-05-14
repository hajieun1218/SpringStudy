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
	
	// 수정하기(내용보기)
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	// 비밀번호 읽어오기
	public String databoardGetPassword(int no) {
		return mapper.databoardGetPassword(no);
	}
	
	// 수정하기
	public void databoardUpdate(DataBoardVO vo) {
		mapper.databoardUpdate(vo);
	}
	
	// 저장된 파일 가져오기 (파일도 삭제하기위해)
	public DataBoardVO databoardFileInfoData(int no){
		return mapper.databoardFileInfoData(no);
	}
	
	// 삭제하기
	// DAO에서 매개변수는 내 마음대로 받아서 사용할 수 있음!
	// pwd체크는 DAO에서 처리해도 되고 , Controller에서 처리해도 된다
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			mapper.databoardDelete(no);
			bCheck=true;
		}
		return bCheck;
	}
}
