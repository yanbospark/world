package com.dodonew.aop;

/**
 * AOP入口
 * @author yanbo
 */
public class AopEnter {
	
	private static AopEnter aopEnter;
	private Transaction transaction;//事务
	private TimeCount time;//计时
	
	private AopEnter(){
		//创建对象可以通过解析注解，然后反射创建对象
		transaction=Transaction.getTransaction();
		time=new TimeCount();
	}
	
	public static AopEnter getInstance(){
		if(aopEnter==null) aopEnter=new AopEnter();
		return aopEnter;
	}
	
	public void before(){
		if(time!=null){
			time.beforeTime();
		}
		if(transaction!=null){
			transaction.getSession();
		}
	}
	
	public void after(){
		if(transaction!=null){
			transaction.closeSession();
		}
		if(time!=null){
			time.endTime();
		}
	}
	
}
