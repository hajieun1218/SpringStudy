package com.sist.spring2;

public interface Hello {
	public void display();
	
	// ������ �޼ҵ� (�տ� default) => Java 1.8����
	public default void sayHello(){}
	public static void sayHello2(){}
}
