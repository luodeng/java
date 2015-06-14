package com.roden.java.util;

import java.applet.Applet;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

public class WebBrower extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws URISyntaxException,
			IOException {
		// jdk1.6以上
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URI("http://www.baidu.com"));
			desktop.open(new File("D:\\Program Files\\KuGou2012\\KuGou.exe"));
			desktop.open(new File("D:\\Program Files\\KuGou2012"));
			desktop.edit(new File("D:\\我的文档\\桌面\\新建文本文档.txt"));
			desktop.print(new File("D:\\我的文档\\桌面\\新建文本文档.txt"));
		}

	}
}