package com.roden.java.thread;
/*
 * 
 * 在线上Java程序中经常遇到进程程挂掉，一些状态没有正确的保存下来，这时候就需要在JVM关掉的时候执行一些清理现场的代码。Java中得ShutdownHook提供了比较好的方案。
　　JDK在1.3之后提供了Java Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在以下几种场景被调用：
	1）程序正常退出
	2）使用System.exit()
	3）终端使用Ctrl+C触发的中断
	4）系统关闭
	5）使用Kill pid命令干掉进程
	注：在使用kill -9 pid是不会JVM注册的钩子不会被调用。
	在JDK中方法的声明：
	public void addShutdownHook(Thread hook)
	参数
	hook -- 一个初始化但尚未启动的线程对象，注册到JVM钩子的运行代码。
	异常
	IllegalArgumentException -- 如果指定的钩已被注册，或如果它可以判定钩已经运行或已被运行
	IllegalStateException -- 如果虚拟机已经是在关闭的过程中
	SecurityException -- 如果存在安全管理器并且它拒绝的RuntimePermission（“shutdownHooks”）

 */

public class TestShutdownHook {
	
	public static void main(String[] args) {
		// 定义线程1
		Thread thread1 = new Thread() {
			public void run() {
				System.out.println("thread1...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		};
		// 定义线程2
		Thread thread2 = new Thread() {
			public void run() {
				System.out.println("thread2...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		};
		// 定义关闭线程
		Thread shutdownThread = new Thread() {
			public void run() {
				System.out.println("shutdownThread...");
			}
		};
		// jvm关闭的时候先执行该线程钩子
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		thread1.start();
		thread2.start();
	}
}
