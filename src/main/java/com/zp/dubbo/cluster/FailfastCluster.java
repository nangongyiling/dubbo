package com.zp.dubbo.cluster;

import com.zp.dubbo.invoke.Invocation;
import com.zp.dubbo.invoke.Invoke;

public class FailfastCluster implements Cluster {

	public String invoke(Invocation invocation) {
		try {
			Invoke invoke = invocation.getInvoke();
			String result = invoke.invoke(invocation);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("invoke fail");
		}
		
	}

}
