package com.roden.java.net;
//Robot类的用法
public class MouseTest {
	public static void main(String[] args) throws Exception {
		java.awt.Robot m1 = new java.awt.Robot();
		/*
		for (int i = 0; i <= 100; i++)// 或者无限循环
		{
			m1.mouseMove((int) (Math.random() * 1000),
					(int) (Math.random() * 1000));
			Thread.sleep(100);
		}
		*/
//		while (true) {
//			m1.mouseMove(100, 100);
//
//			Thread.sleep(100);
//		}
		m1.delay(5000);
		for(int i=0;i<10;i++){
			m1.delay(1000);
		m1.keyPress(65);
		m1.keyPress(10);
		}
		
		
	}
}
