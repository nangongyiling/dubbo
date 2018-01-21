package com.zp.dubbo.invoke;

import java.rmi.RemoteException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.loadBalance.LoadBalance;
import com.zp.dubbo.loadBalance.NodeInfo;
import com.zp.dubbo.rmi.RmiUtil;
import com.zp.dubbo.rmi.SoaRmi;
import com.zp.dubbo.rpc.http.HttpRequest;

/**
 * rmi通讯协议,底层是socket，只能用于java项目
 * @author guitai
 *
 */
public class RmiInvoke implements Invoke{

	public String invoke(Invocation invocation) {
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
		RmiUtil rmiUtil = new RmiUtil();
		//自定义
		SoaRmi soa = rmiUtil.startRmiClient(nodeInfo, "zpsoarmi");
		String result;
		try {
			result = soa.invoke(sendParam.toJSONString());
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
