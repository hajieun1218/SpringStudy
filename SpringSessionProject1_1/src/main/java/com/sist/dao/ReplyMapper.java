package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.*;

public interface ReplyMapper {

	// 댓글 insert
	@SelectKey(keyProperty="no", resultType=int.class, before=true, 
			statement="SELECT NVL(MAX(no)+1,1) as no FROM music_reply2")
	@Insert("INSERT INTO music_reply2(no,mno,id,msg,group_id) VALUES("
		   +"#{no},#{mno},#{id},#{msg},"
		   +"(SELECT NVL(MAX(group_id)+1,1) FROM music_reply2))")
	public void replyInsert(ReplyVO vo);
	
	// 댓글 출력
	@Select("SELECT no,mno,id,msg,TO_CHAR(regdate,'yyyy-MM-DD HH24:MI:SS') as dbday,group_tab FROM music_reply2 "
		   +"WHERE mno=#{mno} "
		   +"ORDER BY group_id DESC,group_step ASC")
	public List<ReplyVO> replyListData(int mno);
	
	
	// ========================== 대댓글 insert ===============================
	
	// 상위 댓글 정보
	@Select("SELECT group_id,group_step,group_tab "
		   +"FROM music_reply2 "
		   +"WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
	
	// group_step 증가
	@Update("UPDATE music_reply2 SET "
		   +"group_step=group_step+1 "
		   +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void replyGroupStepIncrement(ReplyVO vo);
	
	// 대댓글 insert
	@SelectKey(keyProperty="no", resultType=int.class, before=true, 
			statement="SELECT NVL(MAX(no)+1,1) as no FROM music_reply2")
	@Insert("INSERT INTO music_reply2(no,mno,id,msg,group_id,group_step,group_tab,root) VALUES("
		   +"#{no},#{mno},#{id},#{msg},"
		   +"#{group_id},#{group_step},#{group_tab},#{root})")
	public void replyReplyInsert(ReplyVO vo);
	
	
	// depth 증가
	@Update("UPDATE music_reply2 SET "
		   +"depth=depth+1 "
		   +"WHERE no=#{no}")
	public void replyDepthIncrement(int no);
	
	
	// ========================== 댓글 수정하기 ===============================
	
	@Update("UPDATE music_reply2 SET "
		   +"msg=#{msg} "
		   +"WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
	
	
	// ========================== 댓글 삭제하기 ===============================
	
	/*
	 * 		@SELECT depth, root
	 * 			depth==0  => @DELETE
	 * 			depth!=0  => @UPDATE
	 * 		@UPDATE depth 감소
	 */
	
	@Select("SELECT depth,root FROM music_reply2 "
		   +"WHERE no=#{no}")
	public ReplyVO replyInfoData(int no);
	
	@Delete("DELETE FROM music_reply2 "
		   +"WHERE no=#{no}")
	public void replyDelete(int no);
	
	@Update("UPDATE music_reply2 SET "
		   +"msg=#{msg} "
		   +"WHERE no=#{no}")
	public void replyMsgUpdate(ReplyVO vo);
	
	@Update("UPDATE music_reply2 SET "
		   +"depth=depth-1 "
		   +"WHERE no=#{no}")
	public void replyDepthDecrement(int no);
	
}
