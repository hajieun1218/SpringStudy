package com.sist.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect{
	// execution -> ȣ��ɶ� (Controller���� ȣ���)
	@Around("execution(* com.sist.web.EmpController.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("����� ��û ���: "+jp.getSignature().getName()); // � �Լ��� ȣ��Ǿ�����
		long start=System.currentTimeMillis();
		Object obj=jp.proceed(); // ȣ���� �޼ҵ�
		long end=System.currentTimeMillis();
		System.out.println("����ð�: "+(end-start));
		System.out.println(jp.getSignature().getName()+" ����");
		return obj;
	}
}
