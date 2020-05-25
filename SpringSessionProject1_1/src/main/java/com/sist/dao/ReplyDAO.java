package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	// 댓글 insert
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	
	// 댓글 출력
	public List<ReplyVO> replyListData(int mno) {
		return mapper.replyListData(mno);
	}
	
	// 대댓글
	// REQUIRED => 트랜잭션을 반드시 적용하겠다
	// rollbackFor=Exception.class => System.out.println(ex.getMessage())
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void replyReplyInsert(int pno, ReplyVO vo) {
		ReplyVO pvo=mapper.replyParentInfoData(pno);
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		mapper.replyReplyInsert(vo);
		mapper.replyDepthIncrement(pno);
	}
	
	// 댓글 수정하기
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	
	// 댓글 삭제하기
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void replyDelete(int no) {
		ReplyVO vo=mapper.replyInfoData(no); // depth,root
		if(vo.getDepth()==0) {
			mapper.replyDelete(no);
		}
		else {
			ReplyVO rvo=new ReplyVO();
			rvo.setNo(no);
			rvo.setMsg("관리자가 삭제한 댓글입니다.");
			mapper.replyMsgUpdate(rvo);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
