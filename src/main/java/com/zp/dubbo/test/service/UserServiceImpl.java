package com.zp.dubbo.test.service;

public class UserServiceImpl implements UserService{

	public String eat() {
		System.out.println("test");
		return "test123";
	}

}
