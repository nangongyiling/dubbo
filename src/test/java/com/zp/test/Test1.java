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
		try {
//			 userService= (UserService) user.getObject();
			 UserService userService = app.getBean(UserService.class);
			System.out.println(userService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
