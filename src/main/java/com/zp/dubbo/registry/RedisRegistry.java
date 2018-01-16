package com.zp.dubbo.registry;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.configBean.Protocol;
import com.zp.dubbo.configBean.Registry;
import com.zp.dubbo.configBean.Service;
import com.zp.dubbo.redis.RedisApi;

/**
 * redis注册中心处理类
 * @author guitai
 *
 */
public class RedisRegistry implements BaseRegistry{

	public boolean registry(String param, ApplicationContext application) {
		Protocol protocol = application.getBean(Protocol.class);
		Map<String,Service> services = application.getBeansOfType(Service.class);
		
		Registry registry = application.getBean(Registry.class);
		RedisApi.createJedisPool(registry.getAddress());
		for(Map.Entry<String,Service> entry:services.entrySet()){
			if(StringUtils.equals(entry.getValue().getRef(), param)){
				JSONObject json = new JSONObject();
				json.put("protocol", JSONObject.toJSONString(protocol));
				json.put("service", JSONObject.toJSONString(entry.getValue()));
				
				JSONObject ipport = new JSONObject();
				ipport.put(protocol.getHost()+":"+protocol.getPort(), json);
				//RedisApi.lpush(ipport,param);
			}
		}
		return false;
	}

	//每一次重启生产者都需要把之前注册的信息删掉
	private void lpush(JSONObject json,String key){
		if(RedisApi.exists(key)){
			//最外层的那个beanid，所以只有一个
			Set<String> keys = json.keySet();
			String ipportStr="";
			for(String s:keys){
				ipportStr = s;
			}
			
		}
	}
}
