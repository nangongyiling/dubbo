package com.zp.dubbo.registry;

import java.util.ArrayList;
import java.util.List;
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
				lpush(ipport,param);
			}
		}
		return false;
	}

	//每一次重启生产者都需要把之前注册的信息删掉
	//key是ref(类的全类名,所有生产者都是一样的，非id)
	private void lpush(JSONObject json,String key){
		if(RedisApi.exists(key)){
			//这个是上面传进来的，只有一个ip+port
			Set<String> keys = json.keySet();
			String ipportStr="";
			for(String s:keys){
				ipportStr = s;
			}
			List<String> registryInfo = RedisApi.lrange(key);
			List<String> newRegistry = new ArrayList<String>();
			
			boolean isold = false;
			for(String s:registryInfo){
				JSONObject jo = JSONObject.parseObject(s);
				if(jo.containsKey(ipportStr)){
					isold = true;
					newRegistry.add(json.toJSONString());
				}else{
					newRegistry.add(s);
				}
			}
			if(isold){
				//老机器去重
				if(newRegistry.size()>0){
					RedisApi.del(key);
					//可以直接使用集合转数组
					String[] newRestr = new String[newRegistry.size()];
					for(int i=0;i<newRestr.length;i++){
						newRestr[i] = newRegistry.get(i);
					}
					RedisApi.lpush(key, newRestr);
				}
			}else{
				RedisApi.lpush(key, json.toJSONString());
			}
		}else{
			RedisApi.lpush(key, json.toJSONString());
		}
	}

	public List<String> getRegistry(String param, ApplicationContext application) {
		try {
			Registry registry = application.getBean(Registry.class);
			RedisApi.createJedisPool(registry.getAddress());
			if(RedisApi.exists(param)){
				//获取key对应的list
				List<String> list = RedisApi.lrange(param);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
