package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	// ���
	public List<DataBoardVO> databoardListData(Map map) {
		return mapper.databoardListData(map);
	}
	
	// ��������
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	// Insert
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	// �󼼺���
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	// �����ϱ�(���뺸��)
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	// ��й�ȣ �о����
	public String databoardGetPassword(int no) {
		return mapper.databoardGetPassword(no);
	}
	
	// �����ϱ�
	public void databoardUpdate(DataBoardVO vo) {
		mapper.databoardUpdate(vo);
	}
	
	// ����� ���� �������� (���ϵ� �����ϱ�����)
	public DataBoardVO databoardFileInfoData(int no){
		return mapper.databoardFileInfoData(no);
	}
	
	// �����ϱ�
	// DAO���� �Ű������� �� ������� �޾Ƽ� ����� �� ����!
	// pwdüũ�� DAO���� ó���ص� �ǰ� , Controller���� ó���ص� �ȴ�
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			mapper.databoardDelete(no);
			bCheck=true;
		}
		return bCheck;
	}
}
