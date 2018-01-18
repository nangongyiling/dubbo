package com.zp.dubbo.loadBalance;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 轮训负载均衡
 * @author guitai
 *
 */
public class RoundRobinLoadBalance implements LoadBalance{

	public static Integer index=0;
	
	public NodeInfo doSelect(List<String> registryInfo) {
		synchronized (index) {
			if(index>=registryInfo.size()){
				index = 0;
			}
			String registry = registryInfo.get(index);
			JSONObject registryJo = JSONObject.parseObject(registry);
			
			//就一个值
			Collection<Object> list = registryJo.values();
			JSONObject node = null;
			for(Object o:list){
				node = JSONObject.parseObject(o.toString());
			}
			JSONObject pro = (JSONObject) node.get("protocol");
			NodeInfo nodeInfo = new NodeInfo();
			nodeInfo.setContextPath(pro.getString("contextPath"));
			nodeInfo.setHost(pro.getString("host"));
			nodeInfo.setPort(pro.getString("port"));
			return nodeInfo;
		}
		
	}

}
