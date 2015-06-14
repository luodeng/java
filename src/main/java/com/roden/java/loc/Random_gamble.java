package com.roden.java.loc;


import java.util.*;

//生成一注随机双色球
public class Random_gamble {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>(6);

		int i = 0;
		while (hs.size() < 6) {
			hs.add(new Random().nextInt(33) + 1);
		}
		Iterator<Integer> it = hs.iterator();
		while (it.hasNext()) {
			i++;
			System.out.println("第" + i + "红球号码为" + it.next());
		}

		System.out.println("蓝球号码为" + (new Random().nextInt(16) + 1));

	}

}
