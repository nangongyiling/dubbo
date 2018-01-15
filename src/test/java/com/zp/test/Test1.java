package com.zp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zp.dubbo.configBean.Reference;
import com.zp.dubbo.test.service.UserService;

public class Test1 {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("test.xml");
		//<bean id="userService" class="com.zp.dubbo.test.service.UserServiceImpl"></bean>
		Reference user=app.getBean(Reference.class);
		System.out.println(user);
		try {
//			 userService= (UserService) user.getObject();
			 UserService userService = app.getBean(UserService.class);
			userService.eat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
