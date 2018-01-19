package com.zp.dubbo.remote.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.configBean.Service;

/**
 * 生产者接收消费者的http请求
 * @author guitai
 *
 */
public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -65654586846799606L;

	protected void service(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject requestParam = httpProcess(request,response);
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
					PrintWriter pw;
					pw = response.getWriter();
					pw.write(result.toString());
				}else{
					PrintWriter pw = response.getWriter();
					pw.write("--------------noSuchMethod--------------");
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static JSONObject httpProcess(HttpServletRequest request,HttpServletResponse response) throws IOException{
		StringBuffer sb = new StringBuffer();
		InputStream in = request.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
		String s="";
		while((s=br.readLine())!=null){
			sb.append(s);
		}
		if(sb.toString().length()<=0){
			return null;
		}else{
			return JSONObject.parseObject(sb.toString());
		}
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
