package com.roden.java.loc;
//UDP局域网聊天程序
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class Chat {
	Frame f = new Frame("我的聊天室");
	TextField tfIP = new TextField(15);
	List lst = new List(6);
	DatagramSocket ds;

	public Chat() {
		try {
			ds = new DatagramSocket(3000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Thread(new Runnable() {
			public void run() {
				byte buf[] = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, 1024);
				while (true) {
					try {
						ds.receive(dp);
						lst.add(new String(buf, 0, dp.getLength()) + ":from"
								+ dp.getAddress().getHostAddress(), -1);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}

	public static void main(String args[]) {
		Chat chat = new Chat();
		chat.init();
	}

	public void init() {
		f.setSize(300, 300);
		f.add(lst);
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		p.add("West", tfIP);
		TextField tfData = new TextField(20);
		p.add("East", tfData);
		f.add("South", p);
		f.setVisible(true);
		f.setResizable(false);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ds.close();
				f.setVisible(false);
				f.dispose();
				System.exit(0);

			}
		});
		tfData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte[] buf;
				buf = e.getActionCommand().getBytes();
				System.out.println(e.getActionCommand());
				try {
					DatagramPacket dp = new DatagramPacket(buf, buf.length,
							InetAddress.getByName(tfIP.getText()), 3000);
					ds.send(dp);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				((TextField) e.getSource()).setText("");

			}
		});

	}
}
