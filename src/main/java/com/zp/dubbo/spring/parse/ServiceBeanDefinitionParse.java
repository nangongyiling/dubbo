package com.zp.dubbo.spring.parse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParse implements BeanDefinitionParser{
	
	private Class<?> beanClass;

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public ServiceBeanDefinitionParse(Class<?> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	public BeanDefinition parse(Element element, ParserContext parse) {
		RootBeanDefinition bean = new RootBeanDefinition();
		bean.setBeanClass(beanClass);
		bean.setLazyInit(false);
		String protocol = element.getAttribute("protocol");
		String intf = element.getAttribute("interface");
		String ref = element.getAttribute("ref");
		if(StringUtils.isBlank(protocol)){
			throw new RuntimeException("parse ServiceBeanDefinitionParse protocol is null");
		}
		if(StringUtils.isBlank(intf)){
			throw new RuntimeException("parse ServiceBeanDefinitionParse intf is null");
		}
		if(StringUtils.isBlank(ref)){
			throw new RuntimeException("parse ServiceBeanDefinitionParse ref is null");
		}
		bean.getPropertyValues().addPropertyValue("protocol", protocol);
		bean.getPropertyValues().addPropertyValue("intf", intf);
		bean.getPropertyValues().addPropertyValue("ref", ref);
		return bean;
	}
	
	
}
