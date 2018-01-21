package com.zp.dubbo.configBean;

import org.springframework.beans.factory.InitializingBean;

import com.zp.dubbo.rmi.RmiUtil;

public class Protocol extends BaseConfigBean implements InitializingBean{
	
	private static final long serialVersionUID = 36528216447617294L;

	private String name;
	
	private String port;
	
	private String host;
	
	private String contextpath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getContextpath() {
		return contextpath;
	}

	public void setContextpath(String contextpath) {
		this.contextpath = contextpath;
	}

	public void afterPropertiesSet() throws Exception {
		if("rmi".equalsIgnoreCase(name)){
			RmiUtil util = new RmiUtil();
			util.startRmiServer(host, port, "zpsoarmi");
		}
	}
}
