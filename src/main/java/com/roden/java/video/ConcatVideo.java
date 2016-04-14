package com.roden.java.video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConcatVideo {

	public static void main(String[] args) {
		/*List<String> commend = new ArrayList<String>();  
        commend.add("c:\\ffmpeg\\bin\\ffmpeg");  
        commend.add("-i");  
        commend.add("\"concat:");   
        commend.add("C:\\ffmpeg\\input\\aaa.mp4|");
        commend.add("C:\\ffmpeg\\input\\aaa.mp4");
        commend.add("-c copy");
        commend.add("c:\\ffmpeg\\output\\concat.mp4");  
        ProcessBuilder builder = new ProcessBuilder(commend);  
        builder.command(commend);   
        try {
			builder.start();
		} catch (IOException e) {			
			e.printStackTrace();
		}  */
		String cmd="c:\\ffmpeg\\bin\\ffmpeg -i C:\\ffmpeg\\input\\aaa.mp4 c:\\ffmpeg\\output\\aaatemp.mp4";
    
        try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {			
			e.printStackTrace();
		}      

	}

}
