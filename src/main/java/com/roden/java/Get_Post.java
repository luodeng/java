package com.roden.java;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Get_Post{
public static void main(String[] args) throws IOException {
	// 服务器监听端口号8088
	ServerSocket serverSocket = new ServerSocket(8088);

	// 等待接收请求，这是一个阻塞的方法，当请求到来的时候才会继续向下执行
	Socket socket = serverSocket.accept();

	// 获取请求内容
	InputStream is = socket.getInputStream();
	InputStreamReader reader = new InputStreamReader(is);

	// 输出请求内容
	while (true) {
		System.out.print((char)reader.read());
	}
}
}