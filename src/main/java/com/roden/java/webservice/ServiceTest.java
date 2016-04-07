package com.roden.java.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
/*
 * 参考：<a href="http://www.cnblogs.com/Johness/archive/2013/04/19/3030392.html">真正的轻量级WebService框架——使用JAX-WS(JWS)发布WebService </a>
 */
@WebService
public class ServiceTest {
	public String sayHello(String name){
		return "hello "+name;
	}
	public int sum(int a,int b){
		return a+b;
	}
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8000/java/ServiceTest", new ServiceTest());
		System.out.println("publish success");
		/*
		 * 1、访问：http://localhost:8000/java/ServiceTest?wsdl  发布成功了
		 * 2、cmd窗口执行命令:(用JDK自带的wsimport工具生成webservice客户端代码) -s省略：当前目录，-p省略：无包  -d:生成class
				格式：wsimport -s "src目录" -p "生成类所在包名" -keep "wsdl发布地址"
				示例：wsimport -s E:\Server\git\java\src\main\java -p webservice.auto -keep http://localhost:8000/java/ServiceTest?wsdl
				说明：1）"src目录"地址不可含空格    2）"wsdl发布地址"不要漏了"?wsdl"
		 * 3、ClientTest获取服务
		 */
	}

}
