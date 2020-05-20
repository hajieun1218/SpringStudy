package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 * 		Spring���� �޸� �Ҵ�
 		= @Component : �Ϲ�Ŭ���� (MainClass,~Manager,�ܺο��� ������ �о�ö�)
 		= @Repository : DAO 
 		= @Controller : Model => ~Controller(Spring),~Action(Structs),~Model(��Ʈ�ѷ���  ���������)
 		= @RestController : JSON,XML => (Ajax,React)
 		= @Service : DAO�� ������ �����ؼ� ��� => BI
 		
 		Spring���� DI 
 		= @Autowired �ڵ�����(�̹� ������ ��ü�� �ּҰ��� �־���)
 		= @Resource �ش�id�� �ش��ϴ� ���� �޶�
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
