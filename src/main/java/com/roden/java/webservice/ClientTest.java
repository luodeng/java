package com.roden.java.webservice;

import com.roden.java.webservice.auto.*;
public class ClientTest {

	public static void main(String[] args) {
		com.roden.java.webservice.auto.ServiceTest st=new ServiceTestService().getServiceTestPort();
		String name=st.sayHello("javaee");
		System.out.println(name);
		System.out.println(st.sum(1, 2));

	}

}
