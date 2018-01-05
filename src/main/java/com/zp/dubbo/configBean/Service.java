package com.zp.dubbo.configBean;

import org.springframework.beans.factory.InitializingBean;

public class Service extends BaseConfigBean implements InitializingBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5230321686343826L;
	
	private String ref;
	
	private String portocol;
	
	private String inf;

	public void afterPropertiesSet() throws Exception {
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getPortocol() {
		return portocol;
	}

	public void setPortocol(String portocol) {
		this.portocol = portocol;
	}

	public String getInf() {
		return inf;
	}

	public void setInf(String inf) {
		this.inf = inf;
	}

}
