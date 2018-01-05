package com.zp.dubbo.spring.parse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ReferenceBeanDefinitionParse implements BeanDefinitionParser{

	private Class<?> beanClass;
	
	public ReferenceBeanDefinitionParse(Class<?> beanClass) {
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
		String id = element.getAttribute("id");
		String intf = element.getAttribute("interface");
		String protocol = element.getAttribute("protocol");
		String loadbalance = element.getAttribute("loadbalance");
		String cluster = element.getAttribute("cluster");
		String retries = element.getAttribute("retries");
		if(StringUtils.isBlank(id)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse id is null");
		}
		if(StringUtils.isBlank(intf)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse intf is null");
		}
		if(StringUtils.isBlank(protocol)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse protocol is null");
		}
		if(StringUtils.isBlank(loadbalance)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse loadbalance is null");
		}
		if(StringUtils.isBlank(cluster)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse cluster is null");
		}
		if(StringUtils.isBlank(retries)){
			throw new RuntimeException("parse ReferenceBeanDefinitionParse retries is null");
		}
		bean.getPropertyValues().addPropertyValue("id", id);
		bean.getPropertyValues().addPropertyValue("intf", intf);
		bean.getPropertyValues().addPropertyValue("protocol", protocol);
		bean.getPropertyValues().addPropertyValue("loadbalance", loadbalance);
		bean.getPropertyValues().addPropertyValue("cluster", cluster);
		bean.getPropertyValues().addPropertyValue("retries", retries);
		return bean;
	}

}
