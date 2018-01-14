package com.zp.dubbo.test.service;

public class TestServiceImpl implements TestService{

	public String eat(String param) {
		System.out.println("TestServiceImpl eat");
		return "test"+param;
	}

}
