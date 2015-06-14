package com.roden.java.net;



import java.io.*;
import java.util.*;

/**
 * 本地文件拷贝，支持断点续传
 * 
 * 
 * 将下载的文件分成多段，每段用一个或多个线程处理，多段下载完毕后，
 * 合并文件，删除多段产生的临时文件即可。
 * @author Kyle
 * 2012-9-26
 */
public class FileMultiCopy {
	private String fileSrcPath;//拷贝的原文件全局路径
	private String fileSrcName;//原文件名称
	private String fileDestPath;//目标地址
	private final int FILE_SPLIT_NUM=10;//分割成几段下载
	private String[] filesplitName=new String[FILE_SPLIT_NUM];//应该要下载的临时文件名称
	private int fileByteLength;//文件长度
	
	/**
	 * 设置要拷贝的原始文件,目标文件路径
	 * @param filesrcPath  原始文件路径
	 * @param filedestPath 目标文件路径
	 */
	FileMultiCopy(String filesrcPath,String filedestPath){
		this.fileSrcPath=filesrcPath;
		this.fileDestPath=filedestPath;	
		fileSrcName = new File(fileSrcPath).getName();
		for(int i=0;i<FILE_SPLIT_NUM;i++){
			filesplitName[i]=fileSrcName+".part"+i;
		}
			
	}	
	/*
	 * 获取原文件的字节大小，以及每个分割文件大小
	 */
	@SuppressWarnings("resource")
	void getFileMulti() {
		try{
			FileInputStream f = new FileInputStream(fileSrcPath);
			fileByteLength = f.available();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 检查下载目录中是否存在原文件的拷贝
	 */
	@SuppressWarnings("resource")
	void checkFileExits(){
		File f = new File(fileDestPath+"\\"+fileSrcName);	
		if(f.exists()){
			System.out.println("要拷贝的文件已经存在!重新下载覆盖原文件请输入Y,不下载退出程序请输入N!");
			Scanner smeg = new Scanner(System.in);
			String meg = smeg.nextLine();
			if("N".equals(meg))
				System.exit(0);
			else{
				//重新下载
				FileMultiLoad();
			}
		}else
			checkTempfile();
		
	}
	/**
	 * 检查下载目录中是否有下载过的临时文件，如果有继续从下个字节下载
	 * 如果没有就重新下载
	 */
	@SuppressWarnings("resource")
	void checkTempfile() {		
		long tempfileByteSize=0;
		int size=fileByteLength/FILE_SPLIT_NUM;	
			//续传功能
			try{
			for(int x=0;x<FILE_SPLIT_NUM;x++){
				//临时文件存在续传
				String filename = fileDestPath+"\\"+filesplitName[x];
				File f = new File(filename);
				if(f.exists()){				
					FileInputStream tempfile=new FileInputStream(filename);
					 tempfileByteSize = tempfile.available();//临时文件的大小
				}else{
					 tempfileByteSize=0;
				}
					//下载过的临时文件的大小是否小于完整片段的大小，而且不是最后一次，小于就续传
					if(tempfileByteSize<(size)&&x<(FILE_SPLIT_NUM-1)){
							
							RandomAccessFile rfilein = new RandomAccessFile(fileSrcPath,"rw");
							RandomAccessFile rfileout = new RandomAccessFile(filename,"rw");
							rfilein.seek((x*size)+tempfileByteSize);
							rfileout.seek(tempfileByteSize);
							
							for(int i=0;i<size-tempfileByteSize;i++){
								rfileout.write(rfilein.read());
							}
							rfileout.close();
							rfilein.close();
						
						}else if((x*(size)+tempfileByteSize)<(fileByteLength)&&x==(FILE_SPLIT_NUM-1)){
							//最后一次分段必须读取到文件最后
							RandomAccessFile rfilein = new RandomAccessFile(fileSrcPath,"rw");
							RandomAccessFile rfileout = new RandomAccessFile(filename,"rw");
							rfilein.seek((x*size)+tempfileByteSize);
							rfileout.seek(tempfileByteSize);
							int i;
							while((i=rfilein.read())!=-1){
								rfileout.write(i);
							}
							rfileout.close();
							rfilein.close();
						}
					
				
			}
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		System.out.println("续传完毕！");
		
	}
	/**
	 * 模拟多线程(分段)下载
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	void FileMultiLoad(){		
		try{
		fileByteLength = new FileInputStream(fileSrcPath).available();
		int byteindex = 0;
		System.out.println("原文件长度为:"+fileByteLength);
			for(int i=0;i<FILE_SPLIT_NUM;i++){
				RandomAccessFile rfilein = new RandomAccessFile(fileSrcPath,"rw");
				RandomAccessFile rfileout = new RandomAccessFile(fileDestPath+"\\"+fileSrcName+".part"+i,"rw");
				int size=fileByteLength/FILE_SPLIT_NUM;				
								
				if(i==0){//第一次读取
					for(int x=0;x<size;x++){		
						
							rfileout.write(rfilein.read());
					}
					rfilein.close();
					rfileout.close();
				}else if(i==(FILE_SPLIT_NUM-1)){//最后一次读完
					byteindex+=size; 
					rfilein.seek(byteindex);
					int ibyte;
					while((ibyte=rfilein.read())!=-1){							
							rfileout.write(ibyte);
					}
					rfilein.close();
					rfileout.close();
				}else{	//中间读
					byteindex+=size; 
					rfilein.seek(byteindex);	
					for(int x=0;x<size;x++){
						rfileout.write(rfilein.read());
					}
				rfilein.close();
				rfileout.close();
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
			System.out.println("下载完毕！");
		
	}
	
	/**
	 * 文件合并功能
	 * @throws Exception
	 */
	void fileCombine(){
		try {
			System.out.println("开始合并");
			RandomAccessFile  raf = new RandomAccessFile(fileDestPath+"\\"+fileSrcName,"rw");
			FileInputStream fin = null;
			byte[] b = new byte[1024];
			@SuppressWarnings("unused")
			int alreadyWrite=0,len ,l=0;
			 for(int i=0;i<FILE_SPLIT_NUM;i++)   
		      {   
		        raf.seek(alreadyWrite);      
		        fin=new FileInputStream(fileDestPath+"\\"+fileSrcName+".part"+i);   
		        while((len=fin.read(b))>0)   
		        {
		          raf.write(b,0,len); 
		        }   
		        fin.close();   
		        alreadyWrite=alreadyWrite+(fileByteLength/FILE_SPLIT_NUM);   
		     }   
		      raf.close();  
		      fin.close();
		      System.out.println("合并完毕！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 删除掉临时文件
	 */
	void delTempfile(){
		 for(int i=0;i<filesplitName.length;i++){
			 File ftemp=new File(fileDestPath+"\\"+filesplitName[i]); 
				 if(ftemp.exists()){
					 System.out.println("正在删除临时："+fileDestPath+"\\"+filesplitName[i]);
					 ftemp.delete();
					 
				 }
				 else
					 System.out.println("临时文件已删除或者没有下载过!");
		 }
		 System.out.println("临时文件删除成功！程序即将结束!");
		 
	}
	
	
	public static void main(String[] args){
		FileMultiCopy filecopy = new FileMultiCopy("E:\\java/input\\高中语文新大纲规定古诗文背诵篇目.doc",
								"e:/java/output");
		filecopy.getFileMulti();
		filecopy.checkFileExits();
		filecopy.fileCombine();
		filecopy.delTempfile();
		
		
				
	}

}
