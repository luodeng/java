package com.roden.java.net;



import java.io.*;

/**
 * 
 * 本地文件分段拷贝,可设置FILE_SPLIT_NUM分割数.
 * 不支持断点续传功能
 * @author kyle
 * 2012-9-26
 */
public class FileMultiCopy2 {
	private String fileSrcPath;//拷贝的原文件全局路径
	private String fileSrcName;//原文件名称
	private String fileDestPath;//目标地址
	private final int FILE_SPLIT_NUM=3;//分割成几段下载
	private int fileByteLength;//文件长度
	
	/**
	 * 设置要拷贝的原始文件,目标文件路径
	 * @param filesrcPath  原始文件路径
	 * @param filedestPath 目标文件路径
	 */
	FileMultiCopy2(String filesrcPath,String filedestPath){
		this.fileSrcPath=filesrcPath;
		this.fileDestPath=filedestPath;	
	//获取原文件的名称
		fileSrcName = new File(fileSrcPath).getName();
	}	

	/**
	 * 模拟多线程(分段)下载
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	void FileMultiLoad() throws Exception{			
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
			System.out.println("下载完毕！");
		
	}
	
	
	/**
	 * 文件合并功能
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	void fileCombine() throws Exception{
		try {
			System.out.println("开始合并");
			RandomAccessFile  raf = new RandomAccessFile(fileDestPath+"\\"+fileSrcName,"rw");
			FileInputStream fin = null;
			byte[] b = new byte[1024];
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
		}
		
		
	}
	/**
	 * 删除掉临时文件
	 */
	void delTempfile(){
		 for(int i=0;i<FILE_SPLIT_NUM;i++){
			 File ftemp=new File(fileDestPath+"\\"+fileSrcName+".part"+i); 
				 if(ftemp.exists()){
					 System.out.println("正在删除临时："+fileDestPath+"\\"+fileSrcName+".part"+i);
					 ftemp.delete();
					 
				 }
				 else
					 System.out.println("临时文件已删除或者没有下载过!");
		 }
		 System.out.println("临时文件删除成功！程序即将结束!");
		 
	}
	
	
	public static void main(String[] args) throws Exception{
		FileMultiCopy2 filecopy = new FileMultiCopy2("e:\\java/input\\高中语文新大纲规定古诗文背诵篇目.doc",
								"e:\\java/output");
		filecopy.FileMultiLoad();
		filecopy.fileCombine();
		filecopy.delTempfile();
		
		
	}

}
