package com.zp.dubbo.remote.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Buffer;

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
		try {
			JSONObject requestParam = httpProcess(request,response);
			if(requestParam!=null){
				String serviceId = requestParam.getString("serviceId");
				String methodName = requestParam.getString("methodName");
				JSONArray paramTypes = requestParam.getJSONArray("paramTypes");
				JSONArray methodParam = requestParam.getJSONArray("methodParam");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
}
