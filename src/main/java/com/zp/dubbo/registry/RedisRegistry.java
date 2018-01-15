package com.zp.dubbo.registry;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.zp.dubbo.configBean.Protocol;
import com.zp.dubbo.configBean.Service;

/**
 * redis注册中心处理类
 * @author guitai
 *
 */
public class RedisRegistry implements BaseRegistry{

	public boolean registry(String param, ApplicationContext application) {
		Protocol protocol = application.getBean(Protocol.class);
		Map<String,Service> services = application.getBeansOfType(Service.class);
		return false;
	}

}
