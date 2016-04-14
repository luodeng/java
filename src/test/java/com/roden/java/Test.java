package com.roden.java;


import java.util.UUID;

import org.apache.log4j.Logger;
public class Test {
	public static  Logger log = Logger.getLogger(Test.class);
		public static void main(String[] args) {
		 
		 log.debug("debug");
		 log.info("info");
		 log.warn("warn");
		 
		 log.error("error");
		 System.out.println(UUID.randomUUID().toString().replace("-", ""));
		 String s="222,";
		 String [] arr=s.split(",");
		 System.out.println(arr.length);
		 for(String str:arr)
			 System.out.println(str);
	}

}
