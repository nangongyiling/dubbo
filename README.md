1.spring的整合，自定义标签，接口
2.注册中心的注册（Redis），注册信息和获取信息
3.动态代理，消费者调用的时候拿到的是代理实例，代理的工作是负载均衡和选择调用方式
4.设计模式：代理、策略、委托、观察者
5.集群容错



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
