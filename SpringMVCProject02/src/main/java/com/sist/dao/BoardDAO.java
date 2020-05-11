package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map) {
		return mapper.boardListData(map);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPwd(vo.getNo());
		if(vo.getPwd().equals(db_pwd)) {
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		else {
			bCheck=false;
		}
		return bCheck;
	}
	
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPwd(no);
		if(pwd.equals(db_pwd)) {
			bCheck=true;
			mapper.boardDelete(no);
		}
		else {
			bCheck=false;
		}
		return bCheck;
	}
}
