package com.roden.java.util;

import java.io.*;
import java.net.*;




public class InetAddressTest{
	public static  void main(String args[]) throws Exception{
		System.out.println(InetAddress.getLocalHost());
		System.out.println(InetAddress.getByName("www.baidu.com"));
		InetAddress IT[]=InetAddress.getAllByName("www.taobao.com");
		for(InetAddress i:IT)
			System.out.println(i);
		URL url=new URL("http://weibo.com/u/2734707073");
		System.out.println(url.getContent());
		URLConnection uc;
		FileWriter fw;
		
		    URL ur = new URL("http://weibo.com/u/2734707073");
		    uc = ur.openConnection();
		    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream())); //获取源文件
		fw = new FileWriter("E:\\java/output/url.txt"); //存入本地文件中
		while(true){
		    String temp = br.readLine(); 
		    if(temp == null)  break; 
		    fw.write(temp);
		} 
		fw.close();
		br.close();
		
		}

	}
	

