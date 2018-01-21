package com.zp.dubbo.rmi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.configBean.Service;


public class SoaRmiImpl extends UnicastRemoteObject implements SoaRmi{

	/**
	 * 
	 */
	private static final long serialVersionUID = 626542986201076177L;

	protected SoaRmiImpl() throws RemoteException {
		super();
	}

	public String invoke(String param) throws RemoteException{
		try{
			JSONObject requestParam = JSONObject.parseObject(param);
			if(requestParam!=null){
				String serviceId = requestParam.getString("serviceId");
				String methodName = requestParam.getString("methodName");
				JSONArray paramTypes = requestParam.getJSONArray("paramTypes");
				JSONArray methodParamJa = requestParam.getJSONArray("methodParam");
				Object[] objs=null;
				if(methodParamJa!=null){
					objs = new Object[methodParamJa.size()];
					for(int i=0;i<methodParamJa.size();i++){
						objs[i] = methodParamJa.get(i);
					}
				}
				
				//拿到spring的上下文
				ApplicationContext application = Service.getApplicationContext();
				//拿到服务层的实例
				Object service = application.getBean(serviceId);
				
				Method method = getMethod(service,methodName,paramTypes);
				
				if(method!=null){
					Object result;
					result = method.invoke(service, objs);
					return result.toString();
				}else{
					
					return "--------------noSuchMethod--------------";
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null; 
	}

	private Method getMethod(Object obj,String methodName,JSONArray arry ){
		Method[] methods = obj.getClass().getMethods();
		List<Method> sameName = new ArrayList<Method>();
		for(Method method:methods){
			if(methodName.trim().equals(method.getName())){
				sameName.add(method);
			}
		}
		//只有一个，直接返回
		if(sameName.size() == 1){
			return sameName.get(0);
		}
		
		if(sameName.size() > 1){
			zp:for(Method method:sameName){
				Class<?>[] params = method.getParameterTypes();
				
				//参数个数比较
				if(params.length !=arry.size()){
					continue;
				}
				for(int i=0;i<params.length;i++){
					if(!params[i].toString().equals(arry.getString(0))){
						continue zp;
					}
				}
				return method;
			}
		}
		return null;
	}
}
