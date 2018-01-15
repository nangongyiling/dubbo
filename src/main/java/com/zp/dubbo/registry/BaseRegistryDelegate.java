package com.zp.dubbo.registry;

import org.springframework.context.ApplicationContext;

import com.zp.dubbo.configBean.Registry;

/**
 * 生产者通过代理把信息注册到注册中心
 * @author guitai
 *
 */
public class BaseRegistryDelegate {
	
	public static void registry(String ref,ApplicationContext application){
		Registry registry = application.getBean(Registry.class);
		String protocol = registry.getProtocol();
		BaseRegistry registryBean = Registry.getRegistryMap().get(protocol);
		registryBean.registry(ref, application);
	}
}
