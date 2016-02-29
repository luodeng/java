package com.roden.java.framework.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHander  extends IoHandlerAdapter  {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {		
		System.out.println("client接受信息:"+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {		
		System.out.println("client发送信息:"+message.toString());
	}

}

