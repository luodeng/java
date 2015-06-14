package com.roden.java.loc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//计算器
public class Calculator implements ActionListener {

	static String b = null;
	JFrame jf = new JFrame("计算器");
	JTextField tf = new JTextField();

	public void init() {
		Container c = jf.getContentPane();
		tf.setHorizontalAlignment(JTextField.RIGHT);
		c.add(tf, "North");
		JPanel pn1 = new JPanel();
		c.add(pn1, "Center");
		pn1.setLayout(new GridLayout(5, 4));
		JButton b = new JButton("1");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("2");// 可用for循环处理
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("3");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("+");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("4");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("5");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("6");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("-");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("7");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("8");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("9");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("*");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("0");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton(".");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("%");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("/");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("N");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("C");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("B");
		b.addActionListener(this);
		pn1.add(b);
		b = new JButton("=");
		b.addActionListener(this);
		pn1.add(b);
		tf.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {

			}

			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 10) {
					String f = tf.getText();

					tf.setText(null);
					byte[] s = f.getBytes();
					for (int i = 0; i < s.length; i++) {
						if (s[i] == '+') {
							String str[] = f.split("\\+");
							tf.setText(f.valueOf(Double.parseDouble(str[0])
									+ Double.parseDouble(str[1])));
						}
						if (s[i] == '-') {
							String str[] = f.split("\\-");
							tf.setText(f.valueOf(Double.parseDouble(str[0])
									- Double.parseDouble(str[1])));
						}
						if (s[i] == '*') {
							String str[] = f.split("\\*");
							tf.setText(f.valueOf(Double.parseDouble(str[0])
									* Double.parseDouble(str[1])));
						}
						if (s[i] == '/') {
							String str[] = f.split("\\/");
							tf.setText(f.valueOf(Double.parseDouble(str[0])
									/ Double.parseDouble(str[1])));
						}
						if (s[i] == '%') {
							String str[] = f.split("\\%");
							tf.setText(f.valueOf(Double.parseDouble(str[0])
									% Double.parseDouble(str[1])));
						}

					}

				}
			}
		});
		jf.setLocation(500, 300);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(200, 300);
		jf.setVisible(true);
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("=")) {
			b = tf.getText();

			tf.setText(null);
			byte[] s = b.getBytes();
			for (int i = 0; i < s.length; i++) {
				if (s[i] == '+') {
					String str[] = b.split("\\+");
					tf.setText(b.valueOf(Double.parseDouble(str[0])
							+ Double.parseDouble(str[1])));
				}
				if (s[i] == '-') {
					String str[] = b.split("\\-");
					tf.setText(b.valueOf(Double.parseDouble(str[0])
							- Double.parseDouble(str[1])));
				}
				if (s[i] == '*') {
					String str[] = b.split("\\*");
					tf.setText(b.valueOf(Double.parseDouble(str[0])
							* Double.parseDouble(str[1])));
				}
				if (s[i] == '/') {
					String str[] = b.split("\\/");
					tf.setText(b.valueOf(Double.parseDouble(str[0])
							/ Double.parseDouble(str[1])));
				}
				if (s[i] == '%') {
					String str[] = b.split("\\%");
					tf.setText(b.valueOf(Double.parseDouble(str[0])
							% Double.parseDouble(str[1])));
				}
			}
		} else if (e.getActionCommand().equals("C"))
			tf.setText(null);
		else if (e.getActionCommand().equals("B")) {
			b = b.substring(0, b.length() - 1);
			tf.setText(b);
		} else if (e.getActionCommand().equals("N")) {
			b = null;
			tf.setText(null);
			tf.setText("作者:罗登     QQ 171937805");
		} else {
			b = (tf.getText() + e.getActionCommand());
			tf.setText(tf.getText() + e.getActionCommand());
		}

	}

	public static void main(String args[]) {

		new Calculator().init();
	}
}
