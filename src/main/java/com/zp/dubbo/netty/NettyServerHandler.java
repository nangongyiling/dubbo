package com.zp.dubbo.netty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketAddress;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zp.dubbo.configBean.Service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

@SuppressWarnings("deprecation")
public class NettyServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.bind(ctx, localAddress, promise);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(ctx, msg);
		ByteBuf bf = (ByteBuf) msg;
		byte[] bytes = new byte[bf.readableBytes()];
		String s = new String(bytes,"UTF-8");
		System.out.println("NIO read meg="+s);
		bf.release();
		
		String result = invoke(s);
		ByteBuf bb = ctx.alloc().buffer(4*result.length());
		bb.writeBytes(result.getBytes());
		ctx.writeAndFlush(bb);
		ctx.close();
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
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.close(ctx, promise);
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.connect(ctx, remoteAddress, localAddress, promise);
	}

	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.deregister(ctx, promise);
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.disconnect(ctx, promise);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.flush(ctx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerRemoved(ctx);
	}

	@Override
	public boolean isSharable() {
		// TODO Auto-generated method stub
		return super.isSharable();
	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.read(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.write(ctx, msg, promise);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
}
