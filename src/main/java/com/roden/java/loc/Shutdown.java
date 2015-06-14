package com.roden.java.loc;
//定时关机
/*      at 20:36 Shutdown -s 
 *      Shutdown -a  
 *      Shutdown -f-s
 *      Shutdown -t 10
 *      Shutdown -i
 *      -r  -l
 * 
 */
import javax.swing.JOptionPane;

public class Shutdown {

	public static void main(String[] args) throws Exception {

		Runtime.getRuntime().exec(
				"at " + JOptionPane.showInputDialog(null, "输入关机时间,格式为00:00", "定时关机程序", 1,null,null,"00:00")
						+ " Shutdown -s");
	}

}
