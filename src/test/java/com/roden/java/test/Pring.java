package com.roden.java.test;

public class Pring {

	public static void main(String[] args) {
		print(5);

	}

	public static void print(int num) {
		for (int i = 0; i < num; i++) {
			int temp = 1;
			int n = num;
			while (n > i) {
				System.out.print(" ");
				n--;
			}
			while (temp < i * 2 + 1) {
				System.out.print("*");
				temp++;
			}
			System.out.println("*");

		}
		for (int i = num-2; i >= 0; i--) {
			int temp = 1;
			int n = num;
			while (n > i) {
				System.out.print(" ");
				n--;
			}
			while (temp < i * 2 + 1) {
				System.out.print("*");
				temp++;
			}
			System.out.println("*");

		}
	}
}
