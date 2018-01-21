package com.zp.dubbo.invoke;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.loadBalance.LoadBalance;
import com.zp.dubbo.loadBalance.NodeInfo;
import com.zp.dubbo.rpc.http.HttpRequest;

public class HttpInvoke implements Invoke {

	public String invoke(Invocation invocation) throws Exception {
		try {
			List<String> registryInfo = invocation.getReference().getList();
			String loadBalance = invocation.getReference().getLoadbalance();
			LoadBalance loadBalanceInfo = invocation.getReference().getLoadBalanceMap().get(loadBalance);
			NodeInfo nodeInfo = loadBalanceInfo.doSelect(registryInfo);
			
			//根据serviceId调用生产者的service实例
			//根据methodName和methodParam调用生产者实例的对应方法
			//
			JSONObject sendParam = new JSONObject();
			sendParam.put("methodName", invocation.getMethod().getName());
			sendParam.put("methodParam", invocation.getObjs());
			sendParam.put("serviceId", invocation.getReference().getId());
			sendParam.put("paramTypes", invocation.getMethod().getParameterTypes());
			String url = "http://"+nodeInfo.getHost()+":"+nodeInfo.getPort()+nodeInfo.getContextPath();
			//http调用生产者服务
			String result = HttpRequest.sendPost(url, sendParam.toJSONString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
