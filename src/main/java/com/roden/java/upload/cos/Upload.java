package com.roden.java.upload.cos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;

public class Upload extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filepath = request.getRealPath("/upload_file") + "\\";
		//String filepath ="E:/java/file/";		
		MultipartRequest multi = new MultipartRequest(request, filepath, // 文件上传后保存的位置
				1000 * 1024 * 1024, // 允许的最大文件大小
				"utf-8" // 编码
		);
		System.out.println("文件名：" + multi.getFilesystemName("f"));
		System.out.println("文件描述：" + multi.getParameter("desc"));		
	}
}