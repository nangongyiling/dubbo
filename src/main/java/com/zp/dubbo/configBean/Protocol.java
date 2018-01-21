package com.zp.dubbo.configBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zp.dubbo.netty.NettyUtil;
import com.zp.dubbo.rmi.RmiUtil;

/**
 * applicationListener=在spring加载之后再启动netty，不然tomcat启动失败
 * @author guitai
 *
 */
public class Protocol extends BaseConfigBean implements InitializingBean,ApplicationContextAware
							,ApplicationListener<ContextRefreshedEvent>{
	
	private static final long serialVersionUID = 36528216447617294L;

	private String name;
	
	private String port;
	
	private String host;
	
	private String contextpath;
	
	private static ApplicationContext applicationContext;

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

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Protocol.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 需要开启一个新的线程
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(ContextRefreshedEvent.class.getName().equals(event.getClass().getName())){
			if("netty".equalsIgnoreCase(name) || StringUtils.isBlank(name)){
				new Thread(new Runnable(){
					public void run() {
						try {
							NettyUtil.startServer(port);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
	
}
