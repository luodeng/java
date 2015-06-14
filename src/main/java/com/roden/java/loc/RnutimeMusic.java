package com.roden.java.loc;


import java.util.*;

//定时播放歌曲
public class RnutimeMusic {
	@SuppressWarnings({ "static-access", "resource" })
	public static void main(String[] args) throws Exception {
		int h, m;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入定时的小时和分钟,格式为小时 回车分钟");
		h = sc.nextInt();
		m = sc.nextInt();
		
		while (true) {
			Calendar c1 = Calendar.getInstance();
			System.out.println(new Date());
			
			if (c1.get(c1.HOUR_OF_DAY) == h && c1.get(c1.MINUTE) == m) {
				System.out.println("定时时间到");
				Process p = null;
				p = Runtime
						.getRuntime()
						.exec("C:\\Program Files\\Windows Media Player/wmplayer.exe E:\\java/input/a.mp3");
				Thread.sleep(30000);
				p.destroy();
				System.exit(0);
			}
			Thread.sleep(1000);
		}
	}
}