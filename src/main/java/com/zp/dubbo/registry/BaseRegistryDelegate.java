package com.zp.dubbo.registry;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.zp.dubbo.configBean.Registry;

/**
 * 生产者通过代理把信息注册到注册中心
 * @author guitai
 *
 */
public class BaseRegistryDelegate {
	
	/**
	 * 注册信息
	 * @param ref
	 * @param application
	 */
	public static void registry(String ref,ApplicationContext application){
		Registry registry = application.getBean(Registry.class);
		String protocol = registry.getProtocol();
		BaseRegistry registryBean = Registry.getRegistryMap().get(protocol);
		registryBean.registry(ref, application);
	}
	
	/**
	 * 获取数据
	 * @param id
	 * @return
	 */
	public static List<String> getRegistry(String id,ApplicationContext application){
		Registry registry = application.getBean(Registry.class);
		String protocol = registry.getProtocol();
		BaseRegistry baseRegistry = registry.getRegistryMap().get(protocol);
		return baseRegistry.getRegistry(id, application);
	}
}
