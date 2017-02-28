package com.dodonew.aop;

/**
 * 模拟事务过程
 * 
 * @author yanbo
 */
public class Transaction {
	
	private Transaction(){};
	
	public static Transaction getTransaction(){
		return new Transaction();
	}
	
	public void getSession(){
		System.out.println("开启事务");
	}
	
	public void closeSession(){
		System.out.println("关闭事务");
	}
	
}
