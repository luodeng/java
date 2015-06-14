


package com.roden.java.upload.cos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filepath = request.getRealPath("/upload_file") + "\\";
		//String filepath ="E:\\java\\file\\";
		String filename = request.getParameter("filename");
		filename = new String(filename.getBytes("iso-8859-1"),("GBK"));
		System.err.println(filename);
		OutputStream o = response.getOutputStream();
		byte b[] = new byte[4048];
		File fileLoad = new File(filepath, filename);
		response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"),("iso-8859-1")));
		response.setContentType("application/x-tar");
		long fileLength = fileLoad.length();
		String length = String.valueOf(fileLength);
		response.setHeader("Content_Length", length);
		FileInputStream in = new FileInputStream(fileLoad);
		int n = 0;
		while ((n = in.read(b)) != -1) {
			o.write(b, 0, n);
		}
	}

}