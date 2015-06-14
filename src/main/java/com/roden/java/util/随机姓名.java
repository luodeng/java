package com.roden.java.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class 随机姓名 {
	public static void main(String args[]) throws UnsupportedEncodingException {
		String name = "";
		Random r = new Random();
		for (int i = 0, n = 2 + (r.nextInt(8) == 0 ? 0 : 1); i < n; i++) {
			String str = null;
			int highPos, lowPos;
			Random random = new Random(System.currentTimeMillis() + i);
			highPos = 176 + Math.abs(random.nextInt(39));
			lowPos = 161 + Math.abs(random.nextInt(93));
			byte[] b = new byte[2];
			b[0] = (new Integer(highPos)).byteValue();
			b[1] = (new Integer(lowPos)).byteValue();
			str = new String(b, "GBK");
			name += str;
		}
		System.out.println(name);
	}

}
