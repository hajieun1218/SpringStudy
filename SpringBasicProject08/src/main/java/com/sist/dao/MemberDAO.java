package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
  		Model => Spring���� Controller => BoardController  @Controller
  		VO
  		DAO       @Repository
  		Service   @Service
  		Manager   @Component
  		=============================================== Model => VO ���� �������� Spring�� ���� (annotation�� �ö�)
  		
  		
  		���� (Spring ��ü����(�����ֱ�))
	  		1) c: �Ǵ� <constructor-arg> �� �ִ��� Ȯ�� (�ִٸ� ���� ���� ���� �� �޸� �Ҵ�)
	  		      �޸��Ҵ� (��ϵ� ��� Ŭ���� �޸� �Ҵ�) --> Map�� ����
	  		2) p: �Ǵ� <property> �� �ִ��� Ȯ�� (DI�� ���翩�� Ȯ��)
	  		3) �����ϸ� -> setXxx() �޼ҵ忡 ����
	  		4) �޼ҵ� DI�� �����ϴ��� ���� Ȯ�� (init-method)
	  		5) �����ϸ� -> �޼ҵ� ȣ��
	  		-----------------------------------------
	  		6) ���α׷��Ӱ� �ʿ��� �޼ҵ� ȣ�� (����� ���)
	  		   -> ���α׷� ����, ����Ʈ �̵� -> �����̳ʰ� ������ (�������ִ� ��� ��ü�� �Ҹ�)
	  		-----------------------------------------
	  		7) �޼ҵ� ���翩�� Ȯ�� (destroy-method)
	  		8) ��ü �Ҹ�
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
