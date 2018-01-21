package com.zp.dubbo.cluster;

import com.zp.dubbo.invoke.Invocation;

public interface Cluster {
	String invoke(Invocation invocation);
}
