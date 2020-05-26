package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class BoardDAO {
	@Autowired
	private MongoTemplate mt;
	
	// 리스트 출력
	public List<BoardVO> boardListData(int page) {
		List<BoardVO> list=new ArrayList<BoardVO>();
		Query query=new Query(); // find({no:1})  find안에 들어가는 쿼리
		
		// 페이지 나누기
		int rowSize=10;
		int skip=(rowSize*page)-rowSize;
		query.skip(skip).limit(rowSize);
		
		// 정렬  ORDER BY no DESC
		query.with(new Sort(Sort.Direction.DESC, "no")); 
		// query에서 실행한 값을 BoardVO에 채워서 list에 담아라 , 테이블명은 board
		// find : selectList() ==> NoSQL
		list=mt.find(query, BoardVO.class, "board");
		
		return list;
	}
	
	// 총 페이지
	public int boardTotalPage() {
		int total=0;
		Query query=new Query(); // find({})  조건없이 검색할때 (조건이 있으면 괄호안에 조건을 넣어줌)
		int count=(int)mt.count(query, "board"); // SELECT COUNT(*) FROM board
		total=(int)(Math.ceil(count/10.0));
		return total;
	}
	
	// insert
	public void boardInsert(BoardVO vo) {
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC, "no"));
		// findOne : 가장 위에있는거 하나  => sort했으니까 no가 가장 큰거 가져옴
		BoardVO rvo=mt.findOne(query, BoardVO.class, "board"); // 가장 높은 번호의 VO를 읽어옴
		
		vo.setNo(rvo.getNo()+1); // SELECT MAX(no)+1
		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // SYSDATE
		vo.setHit(0);
		
		mt.insert(vo, "board"); // mt에서 vo를 만들고 board테이블에 insert
	}
	
	// 상세보기
	public BoardVO boardDetailData(int no) {
		BoardVO vo=new BoardVO();
		
		BasicQuery query=new BasicQuery("{no:"+no+"}");  // WHERE no=1
		vo=mt.findOne(query, BoardVO.class, "board");  // find:list, findOne:Object
		
		Update update=new Update();
		update.set("hit", vo.getHit()+1); // 조회수 증가
		mt.updateFirst(query, update, "board");
		vo=mt.findOne(query, BoardVO.class, "board"); // 조회수 증가된 값을 다시 받아온다
		
		return vo;
	}
	
	// 수정하기(내용보기)
	public BoardVO boardUpdateData(int no) {
		BoardVO vo=new BoardVO();
		BasicQuery query=new BasicQuery("{no:"+no+"}");  // WHERE no=1
		vo=mt.findOne(query, BoardVO.class, "board"); 
		return vo;
	}
	
	// 수정하기
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		BasicQuery query=new BasicQuery("{no:"+vo.getNo()+"}"); // BasicQuery => WHERE 문장
		// SELECT pwd from board WHERE no=1
		BoardVO dbvo=mt.findOne(query, BoardVO.class, "board");
		
		if(vo.getPwd().equals(dbvo.getPwd())) {
			bCheck=true;
			Update update=new Update();
			update.set("name", vo.getName());
			update.set("subject", vo.getSubject());
			update.set("content", vo.getContent());
			mt.updateFirst(query, update, "board");
		}
		/*else {
			bCheck=false;
		}*/
		
		return bCheck;
	}
	
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck=false;
		BasicQuery query=new BasicQuery("{no:"+no+"}"); // BasicQuery => WHERE 문장
		// SELECT pwd from board WHERE no=1
		BoardVO dbvo=mt.findOne(query, BoardVO.class, "board");
		
		if(pwd.equals(dbvo.getPwd())) {
			bCheck=true;
			mt.remove(query, "board");
		}
		/*else {
			bCheck=false;
		}*/
		
		return bCheck;
	}
}
