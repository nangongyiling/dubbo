package com.zp.dubbo.configBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zp.dubbo.registry.BaseRegistryDelegate;

/**
 * 一个service代表一个dubbo的配置
 * @author guitai
 *
 */
public class Service extends BaseConfigBean implements InitializingBean,ApplicationContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5230321686343826L;
	
	private String ref;
	
	private String protocol;
	
	private String intf;
	
	private static ApplicationContext application;

	public void afterPropertiesSet() throws Exception {
		BaseRegistryDelegate.registry(ref, application);
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIntf() {
		return intf;
	}

	public void setIntf(String intf) {
		this.intf = intf;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Service.application = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return application;
	}
}
