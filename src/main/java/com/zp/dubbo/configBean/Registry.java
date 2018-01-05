package com.zp.dubbo.configBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Registry extends BaseConfigBean implements InitializingBean,ApplicationContextAware{
	
	public ApplicationContext application;
	
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
}
