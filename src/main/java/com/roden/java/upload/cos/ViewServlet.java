package com.roden.java.upload.cos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>下载</TITLE>");
		out.println("  </HEAD>");		
		out.println("  <BODY>");
		
		String filepath = request.getRealPath("/upload_file") + "\\";
		//String filepath ="E:/java/file/";	
		System.out.println(filepath);
		File file = new File(filepath);
		String[] filenames = file.list();
		for (int i = 0; i < filenames.length; i++) 
		{			
			out.println("<h3><a href=\"download?filename="+filenames[i]+"\">"+filenames[i]+"</a></h3>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}