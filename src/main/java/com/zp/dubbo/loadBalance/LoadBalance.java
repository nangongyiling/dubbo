package com.zp.dubbo.loadBalance;

import java.util.List;

public interface LoadBalance {
	NodeInfo doSelect(List<String> registryInfo);
}
