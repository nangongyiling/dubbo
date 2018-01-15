package com.zp.dubbo.spring.parse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 标签解析器
 * @author lenovo
 *
 */
public class ProtocolBeanDefinitionParse implements BeanDefinitionParser{
	
	private Class<?> beanclass;

	public BeanDefinition parse(Element element, ParserContext parse) {
		RootBeanDefinition bean = new RootBeanDefinition();
		bean.setBeanClass(beanclass);
		bean.setLazyInit(false);
		String name = element.getAttribute("name");
		String host = element.getAttribute("host");
		String port = element.getAttribute("port");
		String contextpath = element.getAttribute("contextpath");
		
		if(StringUtils.isBlank(name)){
			throw new RuntimeException("parse ProtocolBeanDefinitionParse name is null");
		}
		if(StringUtils.isBlank(host)){
			throw new RuntimeException("parse ProtocolBeanDefinitionParse host is null");
		}
		if(StringUtils.isBlank(port)){
			throw new RuntimeException("parse ProtocolBeanDefinitionParse port is null");
		}
//		if(StringUtils.isBlank(contextpath)){
//			throw new RuntimeException("parse ProtocolBeanDefinitionParse contextpath is null");
//		}
		bean.getPropertyValues().addPropertyValue("name", name);
		bean.getPropertyValues().addPropertyValue("host", host);
		bean.getPropertyValues().addPropertyValue("port", port);
		bean.getPropertyValues().addPropertyValue("contextpath", contextpath);
		parse.getRegistry().registerBeanDefinition("protocol"+host+port, bean);
		return bean;
	}

	public ProtocolBeanDefinitionParse(Class<?> beanclass) {
		this.beanclass = beanclass;
	}

	public Class<?> getBeanclass() {
		return beanclass;
	}

	public void setBeanclass(Class<?> beanclass) {
		this.beanclass = beanclass;
	}

}
