package com.zp.dubbo.configBean;

import org.springframework.beans.factory.InitializingBean;

public class Reference extends BaseConfigBean implements InitializingBean {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -360409151182792L;
	
	private String id;
	
	private String intf;
	
	private String protocol;
	
	private String loadbalance;
	
	private String cluster;
	
	private String retries;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntf() {
		return intf;
	}

	public void setIntf(String intf) {
		this.intf = intf;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getLoadbalance() {
		return loadbalance;
	}

	public void setLoadbalance(String loadbalance) {
		this.loadbalance = loadbalance;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getRetries() {
		return retries;
	}

	public void setRetries(String retries) {
		this.retries = retries;
	}

	public void afterPropertiesSet() throws Exception {
	}

}
