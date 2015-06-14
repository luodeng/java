package com.roden.java.loc;
import java.net.*;
import java.io.*;

public class TcpServer {

	public static void main(String[] args) {

		try {
			ServerSocket ss = new ServerSocket(8001);
			Socket s = ss.accept();
			InputStream ips = s.getInputStream();
			OutputStream ops = s.getOutputStream();
			ops.write("welcome to www.baidu.com".getBytes());
			byte[] buf = new byte[1024];
			int len = ips.read(buf);
			System.out.println(new String(buf, 0, len));
			ips.close();
			ops.close();
			s.close();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
