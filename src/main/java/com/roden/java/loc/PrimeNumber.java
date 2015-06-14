package com.roden.java.loc;

//将素数写入文件
import java.io.PrintWriter;

public class PrimeNumber {

	public static void main(String[] args) throws Exception {
		PrintWriter pw = new PrintWriter("e:/java/output/素数表.txt");
		int k = 0;
		for (long i = 1; i < 1000; i++) {
			boolean b = true;
			if (i != 1) {
				for (long j = 2; j < (long) Math.sqrt(i) + 1; j++) {
					if (i % j == 0) {
						b = false;
						break;
					}
				}
			}
			if (b) {
				k++;
				pw.print(i + "\t");
				if (k % 5 == 0)
					pw.println();
				//System.out.println(i);
			}
		}
		pw.close();
	}
}
