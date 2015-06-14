package com.roden.java.javac;

import java.io.*;

public class TestInOut implements Runnable {

	Process p = null;

	public TestInOut() throws IOException {
		p = Runtime.getRuntime().exec("java MyTest");
		new Thread(this).start();
	}

	public void send() throws IOException {
		OutputStream ops = p.getOutputStream();
		while (true) {
			ops.write("help\r\n".getBytes());
		}

	}

	public static void main(String[] args) throws Exception {
		TestInOut tio = new TestInOut();
		tio.send();
	}

	public void run() {
		try {
			InputStream in = p.getInputStream();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
			while (true) {
				String strLine = bfr.readLine();
				System.out.println(strLine);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
}

class MyTest {
	public static void main(String[] args) throws IOException {
		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			System.out.println("hi:" + bfr.readLine());

		}
	}
}