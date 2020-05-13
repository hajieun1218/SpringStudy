package com.sist.dao;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
/*
    NO        NOT NULL NUMBER         
	NAME      NOT NULL VARCHAR2(34)   
	SUBJECT   NOT NULL VARCHAR2(1000) 
	CONTENT   NOT NULL CLOB           
	PWD       NOT NULL VARCHAR2(10)   
	REGDATE            DATE           
	HIT                NUMBER         
	FILENAME           VARCHAR2(1000) 
	FILESIZE           VARCHAR2(1000) 
	FILECOUNT          NUMBER    
 */

/*
  	<input type="text" name="names[0]"/>
  	<input type="text" name="names[1]"/>
  	<input type="text" name="names[2]"/>
  	<input type="text" name="names[3]"/>
  	<input type="text" name="names[4]"/>
  	
  	==> List<String> names;
  		배열로 잡으면 List로 받을 수 있다 
  		(jsp에서는 안되고 spring에서만 가능 - jsp에서는 getParameterValues로 받을 수 있음)
 */

@Getter
@Setter
public class DataBoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	private String filename;
	private String filesize;
	private int filecount;
	private List<MultipartFile> files; // 여러개 파일 동시에 받기 위해
}
