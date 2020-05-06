package com.sist.spring2;

public interface Hello {
	public void display();
	
	// 구현된 메소드 (앞에 default) => Java 1.8부터
	public default void sayHello(){}
	public static void sayHello2(){}
}
