package com.zp.dubbo.configBean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zp.dubbo.registry.BaseRegistry;
import com.zp.dubbo.registry.RedisRegistry;

public class Registry extends BaseConfigBean implements InitializingBean,ApplicationContextAware{
	
	public ApplicationContext application;
	
	private static Map<String,BaseRegistry> registryMap = new HashMap<String,BaseRegistry>();
	
	//注册中心的代理
	static{
		registryMap.put("redis", new RedisRegistry());
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3577758684110387L;

	/**
	 * 
	 */
	private String protocol;
	
	private String address;

	public void afterPropertiesSet() throws Exception {
		
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.application = applicationContext;
	}

	public static Map<String, BaseRegistry> getRegistryMap() {
		return registryMap;
	}
	
}
