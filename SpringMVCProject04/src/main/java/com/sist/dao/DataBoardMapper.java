package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataBoardMapper {
	// ���
	@Select("SELECT no,subject,name,regdate,hit,num "
		   +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		   +"FROM (SELECT no,subject,name,regdate,hit "
		   +"FROM spring_databoard ORDER BY no DESC)) "
		   +"WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	// ��������
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	public int databoardTotalPage();
	
	// INSERT
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
	@Insert("INSERT INTO spring_databoard VALUES("
		   +"#{no},#{name},#{subject},#{content},#{pwd},"
		   +"SYSDATE,0,#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	// ��ȸ�� ����
	@Update("UPDATE spring_databoard SET "
		   +"hit=hit+1 "
		   +"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	// �󼼺���
	@Select("SELECT no,name,subject,content,regdate,hit,filename,filesize,filecount "
		   +"FROM spring_databoard "
		   +"WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	// ��й�ȣ �о����
	@Select("SELECT pwd FROM spring_databoard "
		   +"WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	// �����ϱ�
	@Update("UPDATE spring_databoard SET "
		   +"name=#{name},subject=#{subject},content=#{content} "
		   +"WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	
	// ����� ���� �������� (���ϵ� �����ϱ�����)
	@Select("SELECT filename,filecount FROM spring_databoard "
		   +"WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	
	// �����ϱ�
	@Delete("DELETE FROM spring_databoard "
		   +"WHERE no=#{no}")
	public void databoardDelete(int no);
}
