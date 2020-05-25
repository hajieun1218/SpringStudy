package com.sist.dao;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
/*
 *  NO         NOT NULL NUMBER       
	MNO                 NUMBER       
	ID         NOT NULL VARCHAR2(20) 
	MSG        NOT NULL CLOB         
	REGDATE             DATE         
	GROUP_ID            NUMBER       
	GROUP_STEP          NUMBER       
	GROUP_TAB           NUMBER       
	ROOT                NUMBER       
	DEPTH               NUMBER
 */
@Getter
@Setter
public class ReplyVO {
	private int no;
	private int mno;
	private String id;
	private String msg;
	private Date regdate;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	private String dbday;
}
