package com.zp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParseTest {
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:test.xml");
		String[] ss = app.getBeanDefinitionNames();
		for(String s:ss){
			System.out.println(s);
		}
		System.out.println(app.getBean("Registry127.0.0.1"));
		
	}
}
