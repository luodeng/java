package com.roden.java.algorithm.sort;

//冒泡排序
public class BubbleSorting {

	public static void main(String[] args) {
		int[] list = { 1, 5, 4, 2, 3, 8, 9, 7, 6, 12 };
		System.out.println("最初数组");
		for (int i : list)
			// 注意与下面for对比
			System.out.print("  " + i);
		bubbleSort(list);
		System.out.println("\r\n排序后的数组是");
		for (int i = 0; i < list.length; i++)
			System.out.print("\t" + list[i]);
	}

	static void bubbleSort(int[] list) {
		for (int i = 0; i < list.length; i++) {
			boolean flag = true; // 注意flag的作用
			int temp;
			for (int j = 1; j < list.length - i; j++)
				if (list[j - 1] < list[j]) {
					temp = list[j - 1];
					list[j - 1] = list[j];
					list[j] = temp;
					flag = false;
				}
			if (flag)
				break;
		}

	}
}
