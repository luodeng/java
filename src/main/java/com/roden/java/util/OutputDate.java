package com.roden.java.util;

import java.awt.AWTException;
import java.util.Calendar;

public class OutputDate {

	public static void main(String[] args) throws AWTException {		
		java.awt.Robot r = new java.awt.Robot();
		r.delay(5000);
		for (int k = 0; k < 100; k++) {
			Calendar c = Calendar.getInstance();
			String date = c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-"
					+ c.get(c.DATE) + " " + c.get(c.HOUR) + "."
					+ (c.get(c.MINUTE) + 1) + " " + c.get(c.SECOND);
			for (int i = 0; i < date.length(); i++) {
				//r.delay(5000);
				r.keyPress((int) date.charAt(i));				
			}
			r.keyPress(10);
		}
	}
}
