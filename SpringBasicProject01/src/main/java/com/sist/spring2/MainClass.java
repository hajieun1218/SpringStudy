package com.sist.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

// Spring 5.0 버전
// XML 없이 
public class MainClass {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class);
		EmpDAO dao=(EmpDAO)ctx.getBean("empDAO");
		//EmpDAO dao2=ctx.getBean("empDAO", EmpDAO.class); // 제네릭스 이용 => 형변환 안해도 됨
		List<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}
	}
}
