package com.roden.java.loc;

//打印C盘下所有文件

import java.io.*;

public class File_C {
	static int k = 0;
	static Writer r=null;

	public static void main(String args[]) throws Exception {
            r=new PrintWriter("e:/java/output/file.txt");
		//se("F:");
        se("F:/迅雷下载");
		r.close();
		System.out.println("共有文件 " + k + "个");

	}

	public static void se(String s) throws Exception {
		File f = new File(s);
		if (f.isDirectory()) {
			File[] file = f.listFiles();
			for (int i = 0; i < file.length; i++) {
				se(file[i].getAbsolutePath());
			}
		} else {
			r.write(f+"\r\n");
			
		//	System.out.println(f);
			k++;
		}

	}
}
