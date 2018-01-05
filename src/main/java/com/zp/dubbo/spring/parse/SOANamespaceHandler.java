package com.zp.dubbo.spring.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.zp.dubbo.configBean.Protocol;
import com.zp.dubbo.configBean.Reference;
import com.zp.dubbo.configBean.Registry;
import com.zp.dubbo.configBean.Service;

public class SOANamespaceHandler extends NamespaceHandlerSupport{

	public void init() {
		registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParse(Registry.class));
		registerBeanDefinitionParser("service", new ServiceBeanDefinitionParse(Service.class));
		registerBeanDefinitionParser("protocol",new ProtocolBeanDefinitionParse(Protocol.class));
		registerBeanDefinitionParser("reference", new ReferenceBeanDefinitionParse(Reference.class));
	}

}
