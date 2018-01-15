package com.zp.dubbo.registry;

import org.springframework.context.ApplicationContext;

public interface BaseRegistry {
	
	public boolean registry(String param,ApplicationContext application);
}
