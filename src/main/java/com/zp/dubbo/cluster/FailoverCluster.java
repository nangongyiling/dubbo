package com.zp.dubbo.cluster;

import com.zp.dubbo.invoke.Invocation;
import com.zp.dubbo.invoke.Invoke;

public class FailoverCluster implements Cluster{

	public String invoke(Invocation invocation) {
		String retryTimes = invocation.getReference().getRetries();
		Integer times = Integer.valueOf(retryTimes);
		for(int i=0;i<times;i++){
			try {
				Invoke invoke = invocation.getInvoke();
				String result = invoke.invoke(invocation);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		throw new RuntimeException("all retries fail");
	}

}
