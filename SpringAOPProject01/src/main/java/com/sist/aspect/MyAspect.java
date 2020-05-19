package com.sist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/*
 * 	public void emp_select() {
		//getConnection();
		System.out.println("EMP 테이블에서 데이터를 가지고 온다");
		//disConnection();
	}
 */
@Aspect // 메모리 할당 안됨 -> @Component를 써줘야함
@Component
public class MyAspect {
	// 메소드의 리턴형(*:리턴형 어떤게 들어와도 상관없다) 
	// EmpDAO클래스가 가지고있는 메소드 중에 emp_로 시작하는 모든 메소드를 수행하기 전에 실행
	// .. : 매개변수가 있어도 되고, 없어도 되고, 여러개여도 된다
	
	// 메소드 호출 전
	@Before("execution(* com.sist.dao.EmpDAO.emp_*(..))")
	public void getConnection() {
		System.out.println("오라클 연결");
	}
	
	// finally
	@After("execution(* com.sist.dao.EmpDAO.emp_*(..))")
	public void disConnection() {
		System.out.println("오라클 연결 해제");
	}
	
	// 정상 수행이 된 경우 -> 리턴값 출력
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp_*(..))",returning="ret")
	public void returnValue(Object ret) {
		if(ret!=null)
			System.out.println(ret);
	}
	
	// 메소드 수행하는 과정에서 오류가 발생할 경우에 처리 ->  error 출력
	@AfterThrowing(value="execution(* com.sist.dao.EmpDAO.emp_*(..))",throwing="ex")
	public void exceptionValue(Throwable ex) {
		System.out.println(ex.getMessage());
	}
	
	// 어떤 메소드가 호출 되었는지, 어떤 사이트 많이 들어갔는지
	// log파일 제작할 때 많이 사용, 트랜잭션 처리
	@Around("execution(* com.sist.dao.EmpDAO.display())")
	public Object display(ProceedingJoinPoint jp) throws Throwable {
		System.out.println(jp.getSignature().getName());
		long start=System.currentTimeMillis();
		// setAutoCommit(false);
		Object obj=jp.proceed(); // display 호출
		// conn.commit();
		long end=System.currentTimeMillis();
		System.out.println("수행시간: "+(end-start));
		return obj;
	}
}
