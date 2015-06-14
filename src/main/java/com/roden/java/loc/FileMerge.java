package com.roden.java.loc;
import java.io.*;
//视频合并不能播放,音乐合并成功

public  class FileMerge {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception{
		
		FileInputStream in1=new FileInputStream("E:\\java/input/a.mp3");
		FileInputStream in2=new FileInputStream("E:\\java/input/b.mp3");
		//FileInputStream in3=new FileInputStream("E:\\java/input/c.mp3");
		//FileInputStream in4=new FileInputStream("E:\\java/input/d.mp3");
		SequenceInputStream seq1=new SequenceInputStream(in1,in2);
		//SequenceInputStream seq2=new SequenceInputStream(seq1,in3);
		//SequenceInputStream seq3=new SequenceInputStream(seq2,in4);
		BufferedInputStream bin=new BufferedInputStream(seq1);
		DataInputStream din=new DataInputStream(bin);
		
		FileOutputStream out=new FileOutputStream("E:\\java/output/ab.mp3");
		BufferedOutputStream bout=new BufferedOutputStream(out);
		DataOutputStream dout=new DataOutputStream(bout);
		int i;
		while(( i=din.read())!=-1){
			dout.write(i);
			
		}
	
		din.close();
		dout.close();
	}

}
