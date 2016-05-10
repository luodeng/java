package com.roden.java.framework.mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.roden.java.framework.mina.server.MinaTimeServer;

public class MimaTimeClient {

	public static void main(String[] args) {
		  // 创建客户端连接器.
        //NioSocketConnector connector = new NioSocketConnector();
        //udp
        NioDatagramConnector connector = new NioDatagramConnector();  
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec",  new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        //connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
        
        // 设置连接超时检查时间
        connector.setConnectTimeoutCheckInterval(30);
        connector.setHandler(new TimeClientHander());
        
        // 建立连接
        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", MinaTimeServer.PORT));
        // 等待连接创建完成
        cf.awaitUninterruptibly();
        System.out.println("连接成功");
        
        cf.getSession().write("Hi Server!");      	
		
		
		IoSession session = cf.getSession();		
		Scanner sc = new Scanner(System.in);		
		boolean quit = false;		
		while(!quit){			
			String str = sc.next();
			if(str.equalsIgnoreCase("quit")){
				quit = true;
			}
			session.write(str);
		}
		
		//关闭
		if(session!=null){
			if(session.isConnected()){
				// 等待连接断开
				cf.getSession().getCloseFuture().awaitUninterruptibly();
			}
			 // 释放连接
			connector.dispose(true);
		}
		
		 
		//session.getCloseFuture().awaitUninterruptibly();
        //connector.dispose();	
		
		
	}

}
