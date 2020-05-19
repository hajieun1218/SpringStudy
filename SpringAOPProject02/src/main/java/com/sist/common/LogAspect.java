package com.sist.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect{
	// execution -> 호출될때 (Controller에서 호출됨)
	@Around("execution(* com.sist.web.EmpController.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("사용자 요청 기능: "+jp.getSignature().getName()); // 어떤 함수가 호출되었는지
		long start=System.currentTimeMillis();
		Object obj=jp.proceed(); // 호출한 메소드
		long end=System.currentTimeMillis();
		System.out.println("수행시간: "+(end-start));
		System.out.println(jp.getSignature().getName()+" 종료");
		return obj;
	}
}
