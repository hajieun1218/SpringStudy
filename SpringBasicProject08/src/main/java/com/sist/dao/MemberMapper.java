package com.sist.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.*;

//XML ��ü 
public interface MemberMapper {
	/*
	  		<insert id="memberInsert" parameterType="MemberVO">
	  			<selectKey keyProperty="no", resultType="int", order="BEFORE">
	  				SELECT NVL(MAX(no)+1,1) as no FROM spring_member
	  			</selectKey>
	  		</insert>
	 */
	@SelectKey(keyProperty="no", resultType=int.class, before=true, 
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_member")
	@Insert("INSERT INTO spring_member VALUES("
			+"#{no},#{name},#{sex},#{addr},#{tel})")
	public void memberInsert(MemberVO vo);
	// resultType => ������, parameterType => �Ű�����, id => �޼ҵ��
	
	
	@Select("SELECT * FROM spring_member")
	public List<MemberVO> memberAllData();
	
	
	@Select("SELECT * FROM spring_member "
			+"WHERE no=#{no}")
	public MemberVO memberDetailData(int no);
}
