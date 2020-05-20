package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 * 		Spring에서 메모리 할당
 		= @Component : 일반클래스 (MainClass,~Manager,외부에서 데이터 읽어올때)
 		= @Repository : DAO 
 		= @Controller : Model => ~Controller(Spring),~Action(Structs),~Model(컨트롤러가  사용자정의)
 		= @RestController : JSON,XML => (Ajax,React)
 		= @Service : DAO를 여러개 연결해서 사용 => BI
 		
 		Spring에서 DI 
 		= @Autowired 자동주입(이미 생성된 객체의 주소값을 넣어줌)
 		= @Resource 해당id에 해당하는 값을 달라
 		= @Inject
 		= @Import
 */
@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	
	public List<MusicVO> musicListData() {
		return mapper.musicListData();
	}
	
	public MusicVO musicDetailData(int mno) {
		return mapper.musicDetailData(mno);
	}
	
	public int idCount(String id) {
		return mapper.idCount(id);
	}
	
	public String memberGetPassword(String id) {
		return mapper.memberGetPassword(id);
	}
}
