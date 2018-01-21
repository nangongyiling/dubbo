package com.zp.dubbo.netty;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * netty通讯协议
 * @author guitai
 *
 */
public class NettyUtil {
	
	public static void startServer(String port) throws Exception{
		EventLoopGroup baseGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		//固定写法
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(baseGroup,workGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel arg0) throws Exception {
						arg0.pipeline().addLast(new NettyServerHandler());
						
					}
				}).option(ChannelOption.SO_BACKLOG, 128);
			//NIO同步功能必须写的
			ChannelFuture cf = sb.bind(Integer.valueOf(port)).sync();
			//会阻塞，需要新线程来处理
			cf.channel().closeFuture().sync();
		} finally {
			baseGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public static String sendMsg(String host,String port,final String param){
		EventLoopGroup workGroup = new NioEventLoopGroup();
		final StringBuffer sb = new StringBuffer();
		try {
			Bootstrap bs = new Bootstrap();
			bs.group(workGroup);
			bs.channel(NioSocketChannel.class);
			bs.handler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					// TODO Auto-generated method stub
					arg0.pipeline().addLast(new NettyClientHandler(param,sb));
				}
				
			});
			
			ChannelFuture f =bs.connect(host,Integer.valueOf(port)).channel()
					.closeFuture().await();
			
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			workGroup.shutdownGracefully();
		}
		return null;
	}
}
