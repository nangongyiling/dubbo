package com.zp.dubbo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * java的rmi编程必须继承Remote
 * @author guitai
 *
 */
public interface SoaRmi extends Remote{

	public String invoke(String param)throws RemoteException;
}
