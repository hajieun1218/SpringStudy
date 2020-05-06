package com.sist.spring2;
import java.util.*;
// 스프링이 관리하는 클래스가 아니다 => 일반데이터형(int,..), xml에 등록 X
/*
 		스프링에 등록
 		1) XML
 		2) Annotation
 		3) XML + Annotation  ====> 가장 많이 사용
 			=> XML : 라이브러리 등록 (Mybatis, JDBC) => 라이브러리는 자바코드를 볼 수 없으므로 xml로 등록
 			=> Annotation : 사용자 정의 클래스
 			
 
 */
public class EmpVO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private int comm;
	private int sal;
	private int deptno;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
}
