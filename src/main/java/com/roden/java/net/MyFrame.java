package com.roden.java.net;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import java.io.FileOutputStream;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	public MyFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		tk.addAWTEventListener(new MyAWTEventListener(),
				AWTEvent.KEY_EVENT_MASK);
		setTitle("我是一个窗口");
		setDefaultCloseOperation(0x3);
		setSize(250,100);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MyFrame();

	}
}

class MyAWTEventListener implements AWTEventListener {
	
	private boolean controlPressed = false;
	
	private boolean cPressed = false;

	private FileOutputStream out;

	@Override
	public void eventDispatched(AWTEvent event) {
		if (event.getClass() == KeyEvent.class) {
			// 被处理的事件是键盘事件.
			KeyEvent keyEvent = (KeyEvent) event;

			if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
				keyPressed(keyEvent);
			} else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
				keyReleased(keyEvent);
			}
		}
	}

	private void keyPressed(KeyEvent event)  {
		System.out.println(event.getKeyChar());//此为测试用
		try {
			out = new FileOutputStream("E:\\java/output\\key.txt");
			out.write(event.getKeyChar());
			
		} catch (Exception e) {
			
		}
		if (event.getKeyCode() == KeyEvent.VK_CONTROL) 
		{
			controlPressed = true;
		} else if (event.getKeyCode() == KeyEvent.VK_C) 
		{
			cPressed = true;
		}

		if (controlPressed && cPressed) 
		{
			// 当Ctr + C 被按下时, 进行相应的处理.
			System.out.println("Ctr + C");
			
			
		}
	}

	private void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlPressed = false;
		} else if (event.getKeyCode() == KeyEvent.VK_C) {
			cPressed = false;
		}
	}
}