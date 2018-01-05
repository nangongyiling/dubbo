package com.zp.dubbo.spring.parse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParseTest {
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("test.xml");
		System.out.println(app.getBean("registry"));
	}
}
