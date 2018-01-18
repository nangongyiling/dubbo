package com.zp.dubbo.loadBalance;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.configBean.Protocol;

/**
 * 随机负载均衡
 * @author guitai
 *
 */
public class RandomLoadBalance implements LoadBalance{

	public NodeInfo doSelect(List<String> registryInfo) {
		Random random = new Random();
		int index =random.nextInt(registryInfo.size());
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
		nodeInfo.setContextPath(pro.getString("contextpath"));
		nodeInfo.setHost(pro.getString("host"));
		nodeInfo.setPort(pro.getString("port"));
		return nodeInfo;
	}

}
