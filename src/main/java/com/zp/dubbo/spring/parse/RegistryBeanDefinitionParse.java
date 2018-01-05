package com.zp.dubbo.spring.parse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RegistryBeanDefinitionParse implements BeanDefinitionParser{
	
	private Class<?> beanClass;

	public RegistryBeanDefinitionParse(Class<?> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public BeanDefinition parse(Element element, ParserContext parse) {
		RootBeanDefinition bean = new RootBeanDefinition();
		bean.setBeanClass(beanClass);
		bean.setLazyInit(false);
		String protocol = element.getAttribute("protocol");
		String address = element.getAttribute("address");
		if(StringUtils.isBlank(protocol)){
			throw new RuntimeException("parse RegistryBeanDefinitionParse protocol is null");
		}
		if(StringUtils.isBlank(address)){
			throw new RuntimeException("parse RegistryBeanDefinitionParse address is null");
		}
		bean.getPropertyValues().addPropertyValue("address", address);
		bean.getPropertyValues().addPropertyValue("protocol", protocol);
		parse.getRegistry().registerBeanDefinition("Registry"+address, bean);
		return bean;
	}

}
