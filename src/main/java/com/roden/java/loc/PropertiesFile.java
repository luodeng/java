package com.roden.java.loc;
//使用次数限制共享软件实现
import java.util.Properties;
import java.io.*;

public class PropertiesFile {

	public static void main(String[] args) {
		Properties setting = new Properties();
		try {
			setting.load(new FileInputStream("e:/java/output/count.txt"));
		} catch (Exception e) {
			setting.setProperty("Count", new Integer(0).toString());
		}
		int c = Integer.parseInt(setting.getProperty("Count")) + 1;
		System.out.println("这是本程序第" + c + "次使用");
		setting.put("Count", new Integer(c).toString());
		try {
			setting.store(new FileOutputStream("e:/java/output/count.txt"),
					"This program is used:");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
