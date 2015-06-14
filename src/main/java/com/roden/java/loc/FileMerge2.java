package com.roden.java.loc;
import java.io.*;
//视频合并不能播放,音乐合并成功

public class FileMerge2 {

	//@SuppressWarnings("resource")
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception{
		FileInputStream in=new FileInputStream("E:\\java/input/a.mp3");
		RandomAccessFile in1=new RandomAccessFile("E:\\java/input/a.mp3","rw");
		RandomAccessFile in2=new RandomAccessFile("E:\\java/input/b.mp3","rw");
		RandomAccessFile out=new RandomAccessFile("E:\\java/output/ab.mp3","rw");
		
		int i=0;
		while((i=in1.read())!=-1){
			out.write(i);
		}
		out.skipBytes(in.available());
		while((i=in2.read())!=-1){
			out.write(i);
		}
		out.close();
	}
}
