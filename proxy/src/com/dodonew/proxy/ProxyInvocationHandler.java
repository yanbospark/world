package com.dodonew.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dodonew.aop.AopEnter;

public class ProxyInvocationHandler implements InvocationHandler{

	/**
	 * 被代理对象，也叫真实的对象
	 */
	private Object obj;
	
	public ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }
	/**
	 * 创建代理类工厂
	 * @param realObj
	 * @return 生成代理对象，然后通过代理对象调用方法
	 */
	public static Object getProxyInstanceFactory(Object realObj){
		return Proxy.newProxyInstance(realObj.getClass().getClassLoader(), realObj.getClass().getInterfaces(), new ProxyInvocationHandler(realObj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		AopEnter aop=AopEnter.getInstance();
		aop.before();
		method.invoke(obj, args);
		aop.after();
		return null;
	}

}
