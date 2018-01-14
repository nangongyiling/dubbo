package com.zp.dubbo.configBean;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zp.dubbo.invoke.Invoke;
import com.zp.dubbo.proxy.advice.InvokeInvocationHandler;

public class Reference extends BaseConfigBean implements InitializingBean,FactoryBean,ApplicationContextAware {
	
	

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
	
	private static ApplicationContext applicationContext;
	
	private static Map<String,Invoke> invokeMap = new HashMap<String,Invoke>();
	
	private Invoke invoke;

	//调用协议
	static {
		invokeMap.put("http", new HttpInvoke());
		invokeMap.put("netty", null);
		invokeMap.put("rmi", null);
	}

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

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
		
	}

	public Object getObject() throws Exception {
		if(StringUtils.isNotBlank(protocol)){
			invoke = invokeMap.get(protocol);
		}else{
			//实例化Protocol
			Protocol protocol = applicationContext.getBean(Protocol.class);
			if(protocol!=null){
				invoke = invokeMap.get(protocol.getName());
			}else{
				invoke = invokeMap.get("http");
			}
		}
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?> []{Class.forName(intf)},new InvokeInvocationHandler(invoke,this));
	}

	public Class getObjectType() {
		if(StringUtils.isNotBlank(intf)){
			try {
				return Class.forName(intf);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean isSingleton() {
		return true;
	}
	
	public Invoke getInvoke() {
		return invoke;
	}

	public void setInvoke(Invoke invoke) {
		this.invoke = invoke;
	}
}
