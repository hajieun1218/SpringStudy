package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  		Model => Spring에서 Controller => BoardController  @Controller
  		VO
  		DAO       @Repository
  		Service   @Service
  		Manager   @Component
  		=============================================== Model => VO 빼고 나머지는 Spring이 관리 (annotation이 올라감)
  		
  		
  		순서 (Spring 객체관리(생명주기))
	  		1) c: 또는 <constructor-arg> 가 있는지 확인 (있다면 값을 먼저 대입 후 메모리 할당)
	  		      메모리할당 (등록된 모든 클래스 메모리 할당) --> Map에 저장
	  		2) p: 또는 <property> 가 있는지 확인 (DI의 존재여부 확인)
	  		3) 존재하면 -> setXxx() 메소드에 주입
	  		4) 메소드 DI가 존재하는지 여부 확인 (init-method)
	  		5) 존재하면 -> 메소드 호출
	  		-----------------------------------------
	  		6) 프로그래머가 필요한 메소드 호출 (사용자 사용)
	  		   -> 프로그램 종료, 사이트 이동 -> 컨테이너가 닫힌다 (가지고있던 모든 객체가 소멸)
	  		-----------------------------------------
	  		7) 메소드 존재여부 확인 (destroy-method)
	  		8) 객체 소멸
 */
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public List<MemberVO> memberAllData() {
		return mapper.memberAllData();
	}
	
	public MemberVO memberDetailData(int no) {
		return mapper.memberDetailData(no);
	}
	
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
	}
}
