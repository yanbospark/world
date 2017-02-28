package com.dodonew.proxy;

public class ProxyTest {
	
	public static void main(String[] args) {
		Subject subject=(Subject)ProxyInvocationHandler.getProxyInstanceFactory(new ProxySubject());
		subject.work();
	}
	
}
