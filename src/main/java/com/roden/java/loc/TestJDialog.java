package com.roden.java.loc;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
//对话框
public class TestJDialog {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "程序开始启动");
		final JFrame f = new JFrame("TestJDialog");
		f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int retval = JOptionPane.showConfirmDialog(f, "你真的要结束吗",
						"结束程序", JOptionPane.YES_NO_OPTION);
				if (retval == JOptionPane.YES_NO_OPTION)
					System.exit(0);
			}
		});
		f.setSize(200, 200);
		f.setVisible(true);

	}

}
