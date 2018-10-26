package com.lgx.sort;

public class BubbleSort {

	/**
	 * 冒泡排序
	 */

	public static void main(String[] args) {

		int[] bubbles = { 9, 6, 3, 4, 3, 9 };
		int length = bubbles.length;

		// 循环读取数组
		for (int i = 0; i < length; i++) {
			// length-1次循环，交换位置。如果前面的小于后面的，把小的放在后面，即互换位置。如此往复直到这一轮结束。
			for (int j = 0; j < (length - 1); ++j) {
				if (bubbles[j] < bubbles[j + 1]) {
					int aTemp = bubbles[j];
					// 前面的换成后面数值比较大的
					bubbles[j] = bubbles[j + 1];
					// 后面的换成前面数值比较小的
					bubbles[j + 1] = aTemp;
				}
			}
		}

		// 排序完成后的数组
		for (int i = 0; i < bubbles.length; ++i) {
			System.out.print(bubbles[i] + " ");
		}
	}
}
