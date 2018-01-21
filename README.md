生产者存储信息结构
{
	"testServiceImpl":{
		"host:port":{
			"protocol":{},
			"service":{}
		},
		"host:port":{
			"protocol":{},
			"service":{}
		},
		"host:port":{
			"protocol":{},
			"service":{}
		},
}


集群容错方式:{
	1.failover ------ 自动切换到好的节点（dubbo默认）
	2.failfast ------ 直接失败
	3.failsafe ------ 忽略调用结果，继续流程
}


服务发现和剔除暂未完成
