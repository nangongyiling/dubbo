package com.zp.dubbo.proxy.advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.zp.dubbo.configBean.Reference;
import com.zp.dubbo.invoke.Invoke;

/**
 * 动态代理,根据接口名生成动态代理
 * @author guitai
 *
 */
public class InvokeInvocationHandler implements InvocationHandler{

	
	private Invoke invoke;
	
	private Reference reference;
	
	public InvokeInvocationHandler(Invoke invoke,Reference reference){
		this.invoke = invoke;
		this.reference = reference;
	}
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		System.out.println("已经调用了代理");
		//在这个invoke里面最终要调用多个远程的provider
		return null;
	}

}
