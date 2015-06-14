package com.roden.java.net;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.net.*;


import java.io.*;
/**
 * 单线程文件下载功能
 * 
 */

public class DownTest {
	private URL url;
	private HttpURLConnection httpurl;
	private BufferedInputStream bin;
	private BufferedOutputStream bout;
	private String fileName;//文件名称
	private long fileSize;//文件长度
	private long startByte;//第二段文件起始字节位置
	
	@SuppressWarnings("unused")
	private String downfileDir;//下载的目录
	
	DownTest(String filePath,String downfileDir){//下载的文件地址
		try {			
			
			url = new URL(filePath);
			fileName = getFileName(url.getFile());
			httpurl = (HttpURLConnection)url.openConnection();
			fileSize=Long.parseLong(httpurl.getHeaderField("Content-Length"));
			this.downfileDir=downfileDir;
			System.out.println("文件名称："+fileName);
			System.out.println("文件长度为："+fileSize);
			
		} catch (Exception e) {
			System.out.println("网络异常！");
			e.printStackTrace();
		}
	}
	
	
	
	String getFileName(String filePath){//解析文件名称
		String[] str = filePath.split("/");
		for(String filename:str){
			filePath = filename;
		}
		return filePath;
	}
	
	
	 void fileTemp1()throws Exception{//文件分为两块下载，第一块 filename.exe.part1
			 startByte= fileSize/2;
			System.out.println("文件"+fileName+"开始下载，第一段开始");
			bin = new BufferedInputStream(httpurl.getInputStream());
			bout = new BufferedOutputStream(new FileOutputStream("e:\\java/output/"+fileName+".part1"));
			int i,j=0;
			while((i=bin.read())!=-1&&j<=startByte){
				bout.write(i);
				j++;
			}		
			
			bout.close();
			bin.close();
			System.out.println("第一段下载完毕，下载字节数位"+startByte);
			httpurl.disconnect();
	}
	 
	 
	
	void fileTemp2() throws Exception{//文件分为两块下载，第一块 filename.exe.part2
		//设置断点续传的开始位置   
		httpurl = (HttpURLConnection)url.openConnection();
		System.out.println("断点续传开始，第二段起始字节位置是:"+(startByte+1));
		int i;
		httpurl.setRequestProperty("RANGE", "bytes="+(startByte+1)+"-");    
       //设置接受信息    
		httpurl.setRequestProperty("Accept",    
                               "image/gif,image/x-xbitmap,application/msword,*/*");
		RandomAccessFile rand = new RandomAccessFile("e:\\java/output/"+fileName+".part2", "rw");
//		rand.seek(startByte);
		bin = new BufferedInputStream(httpurl.getInputStream());
		while((i=bin.read())!=-1){
			rand.write(i);		
		}
		rand.close();
		bin.close();
		httpurl.disconnect();
		fileTemp("e:\\java/output/"+fileName+".part1","e:\\java/output/"+fileName+".part2");
		
	}
	
	@SuppressWarnings("resource")
	void fileTemp(String file1path,String file2path)throws Exception{//合并临时文件
		System.out.println("文件下载完毕，开始合并：");
		java.io.SequenceInputStream  seqfile = new SequenceInputStream(new FileInputStream(file1path),new FileInputStream(file2path));
		bin = new BufferedInputStream(seqfile);
		bout = new BufferedOutputStream(new FileOutputStream("e:\\java/output/"+fileName));
		int i=0;
		while((i=bin.read())!=-1){
			bout.write(i);
		}
		bout.close();
		bin.close();
		delTemp(new String[]{file1path,file2path});
	}
	
	void delTemp(String[] fileTempName){//删除临时文件
		try{
			for(String tempfile:fileTempName){				
				File f = new java.io.File(tempfile);
				if(f.exists())
					f.delete();
			}
		}catch(Exception e){
			System.out.println("临时文件删除出错！");
			e.printStackTrace();
		}		
	}
	
	
	public static void main(String[] args) throws Exception {
	
		DownTest dtest = new DownTest("http://ww1.sinaimg.cn/large/63439267gw1dy327p1mttj.jpg","E:\\java/output");
		dtest.fileTemp1();
		dtest.fileTemp2();
	
		System.out.println("文件处理完毕！");		
	}	
}
