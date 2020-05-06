package com.sist.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

// Spring 5.0 ����
// XML ���� 
public class MainClass {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class);
		EmpDAO dao=(EmpDAO)ctx.getBean("empDAO");
		//EmpDAO dao2=ctx.getBean("empDAO", EmpDAO.class); // ���׸��� �̿� => ����ȯ ���ص� ��
		List<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}
	}
}
