package com.zp.dubbo.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.zp.dubbo.loadBalance.NodeInfo;

public class RmiUtil {

	/**
	 * 启动rmi服务
	 * @param host
	 * @param port
	 * @param id
	 */
	public void startRmiServer(String host,String port,String id){
		try {
			SoaRmi rmiService = new SoaRmiImpl();
			LocateRegistry.createRegistry(Integer.valueOf(port));
			//rmi://host:port/id
			
			Naming.bind(id, rmiService);
				
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SoaRmi startRmiClient(NodeInfo nodeInfo,String id){
		try {
			return (SoaRmi) Naming.lookup("rmi://"+nodeInfo.getHost()+":"+nodeInfo.getPort()+"/"+id);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
