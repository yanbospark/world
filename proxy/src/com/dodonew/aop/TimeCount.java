package com.dodonew.aop;

/**
 * 计时器
 * @author yanbo
 */
public class TimeCount {
	
	public void beforeTime(){
		System.out.println("开始计时");
	}
	
	public void endTime(){
		System.out.println("结束计时");
	}
}
