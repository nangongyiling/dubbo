package com.zp.dubbo.netty;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

@SuppressWarnings("deprecation")
public class NettyClientHandler extends ChannelInboundHandlerAdapter{
	
	public String sendMsg;
	
	public StringBuffer message;
	
	

	public NettyClientHandler(String sendMsg, StringBuffer message) {
		super();
		this.sendMsg = sendMsg;
		this.message = message;
	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.bind(ctx, localAddress, promise);
	}

	/**
	 * 当我们连接成功之后触发这个方法
	 * 在这个里面完成消息发送
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("----------channelActive---start");
		ByteBuf bb = ctx.alloc().buffer(sendMsg.length()*4);
		bb.writeBytes(sendMsg.getBytes());
		ctx.write(bb);
		ctx.flush();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}

	/**
	 * 服务端有消息发送过来，会触发这个方法
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("---------channelRead-------start");
		ByteBuf bf = (ByteBuf)msg;
		byte[] bytes = new byte[bf.readableBytes()];
		bf.readBytes(bytes);
		String result = new String(bytes);
		System.out.println("server response msg="+result);
		message.append(result);
		bf.release();
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
