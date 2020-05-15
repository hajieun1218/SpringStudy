package com.sist.dao;
import org.springframework.stereotype.Repository;

import com.mongodb.*;
import java.util.*;

@Repository
public class MovieDAO {
	private MongoClient mc; // Connection
	private DB db; // DataBase (XE)
	private DBCollection dbc; // Table
	
	public MovieDAO() {
		try {
			mc=new MongoClient("localhost",27017);
			db=mc.getDB("mydb");
			dbc=db.getCollection("movie");
		} catch(Exception ex) {}
	}
	
	// {mno:100,title:'',...} => BasicDBObject
	public void movieInsert(MovieVO vo) {
		try {
			BasicDBObject obj=new BasicDBObject();
			obj.put("mno", vo.getMno());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			obj.put("genre", vo.getGenre());
			obj.put("grade", vo.getGrade());
			obj.put("director", vo.getDirector());
			obj.put("actor", vo.getActor());
			obj.put("story", vo.getStory());
			
			dbc.insert(obj);
		} catch(Exception ex) {}
	}
	
	//-------------------------------------------------------------
	
	// 목록
	public List<MovieVO> movieListData(int page) {
		List<MovieVO> list=new ArrayList<MovieVO>();
		try {
			int rowSize=12;
			int skip=(rowSize*page)-rowSize; // skip(3) --> 3페이지(12*3)까지 버리고 37번부터 가져옴
			
			// DBCursor : ResultSet
			// skip만큼 버리고 12개(rowSize)를 가져옴
			DBCursor cursor=dbc.find().skip(skip).limit(rowSize);
			
			while(cursor.hasNext()) {
				BasicDBObject obj=(BasicDBObject)cursor.next();
				
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				list.add(vo);
			}
			cursor.close();
		} catch(Exception ex) {}
		return list;
	}
	
	/*
	 * 	NoSQL => SQL이 존재하지 않고, 함수를 이용해서 처리
	 */
	// 총페이지
	public int movieTotalPage() {
		int total=0;
		try {
			// SELECT CEIL(COUNT(*)/12.0) FROM movie
			int count=(int)dbc.count();
			total=(int)(Math.ceil(count/12.0)); // 자바에서 사용하는 올림 메소드
		} catch(Exception ex) {}

		return total;
	}
	
	// 검색결과
	public List<MovieVO> movieFindData(String fd) {
		// SELECT * FROM movie WHERE title LIKE '%fd%'
		// find({"title",{"$regex",".*"+fd}}) --> 정규식을 이용해 찾는다
		List<MovieVO> list=new ArrayList<MovieVO>();
		try {
			BasicDBObject where=new BasicDBObject("title",new BasicDBObject("$regex",".*"+fd)); // like문장
			// ResultSet
			DBCursor cursor=dbc.find(where); // 조건에 해당되는 내용만 찾아달라
			while(cursor.hasNext()) {
				// {} 블록 하나씩 가져올 때
				BasicDBObject obj=(BasicDBObject)cursor.next(); // rs.next()
				
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				list.add(vo);
			}
			cursor.close();
		} catch(Exception ex) {}
		return list;
	}
	
	// 상세보기
	public MovieVO movieDetailData(int mno) {
		MovieVO vo=new MovieVO();
		try {
			// WHERE mno=10
			BasicDBObject where=new BasicDBObject("mno",mno);
			// 전체를 찾을때는 find(), 하나를 찾을때는 findOne()
			BasicDBObject res=(BasicDBObject)dbc.findOne(where);
			
			vo.setMno(res.getInt("mno"));
			vo.setTitle(res.getString("title"));
			vo.setActor(res.getString("actor"));
			vo.setDirector(res.getString("director"));
			vo.setPoster(res.getString("poster"));
			vo.setGenre(res.getString("genre"));
			vo.setGrade(res.getString("grade"));
			vo.setStory(res.getString("story"));
			
		} catch(Exception ex) {}
		return vo;
	}
}
