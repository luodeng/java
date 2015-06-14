package com.roden.java.important;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {

	public static void main(String[] args) throws Exception {
		//.jar  ftp http
		URL[] urls={new URL("file:Hello.class")};
		
		URLClassLoader ucl=new URLClassLoader(urls);
		ucl.loadClass("important.Hello").newInstance();

	}

}
