package com.roden.java.algorithm.sort;

//快速排序,
public class QuickSort {

	static int items[] = { 1, 45, 2, 5, 6, 6, 2, 07, 14, 4 };

	public static void main(String[] args) {
		System.out.println("排序前");
		for (int i : items)
			System.out.print("  " + i);
		quickSort(items, 0, items.length - 1);
		System.out.println("\r\n排序后");
		for (int i : items)
			System.out.print("  " + i);

	}

	static void quickSort(int[] items, int left, int right) {
		int i, j;
		int x, y;
		i = left;
		j = right;
		x = items[(right + left) / 2];
		do {
			while ((items[i] < x) && (i < right))
				i++;
			while ((x < items[j]) && (j > left))
				j--;
			if (i <= j) {
				y = items[i];
				items[i] = items[j];
				items[j] = y;
				i++;
				j--;

			}
		} while (i <= j);
		if (left < j)
			quickSort(items, left, j);
		if (i < right)
			quickSort(items, i, right);
	}
}
