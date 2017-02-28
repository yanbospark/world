package com.dodonew.proxy;

public class ProxySubject implements Subject{

	@Override
	public void work() {
		System.out.println("this is my work!");
	}

}
