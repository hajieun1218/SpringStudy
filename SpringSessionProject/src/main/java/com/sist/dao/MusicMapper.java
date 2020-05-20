package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 * 		public void insert() {}
 		public void update() {}
 		
 		@Transactional
 		public void replyInsert() {
 			try {
 				ssf.openSession(); 
 				insert(); 정상
 				update(); 오류 ========> catch
 				insert(); 정상 
 				commit(); #### Around
 			} catch(Exception ex) {
 				rollback(); #### After-Throwing
 			} finally {
 				conn.setAutiCommit(); #### After
 			}
 		}
 */
public interface MusicMapper {
	// 전체목록
	@Select("SELECT * FROM music_genie ORDER BY mno ASC")
	public List<MusicVO> musicListData();
	
	// 상세보기
	@Select("SELECT * FROM music_genie "
		   +"WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
	
	// 로그인 id 확인
	@Select("SELECT COUNT(*) FROM member "
		   +"WHERE id=#{id}")
	public int idCount(String id);
	
	// 비밀번호 가져오기
	@Select("SELECT pwd FROM member "
		   +"WHERE id=#{id}")
	public String memberGetPassword(String id);
}
