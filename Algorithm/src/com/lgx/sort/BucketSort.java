package com.lgx.sort;

public class BucketSort {

	/**
	 * 简化版的桶排序
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 0-10，11个桶
		int[] buckets = new int[11];
		// 学生分数
		int[] sockets = { 8, 2, 5, 5, 2 };

		// 初始化数组值为0，循环m次
		for (int m = 0; m < 10; m++) {
			buckets[m] = 0;
		}

		// 如果学生分数与数组下标相等，则该数组值加1。
		for (int n = 0; n < 5; n++) {
			buckets[sockets[n]]++;
		}

		System.out.println("====打印查看数组=====");
		for (int i = 0; i < buckets.length; i++) {
			System.out.println("数组下标为:" + i + "对应的值为:" + buckets[i]);
		}

		System.out.println("====从小到大排序=====");
		for (int m = 0; m < 10; m++) {
			// 这个循环很好，我还以为要if判断
			for (int n = 0; n < buckets[m]; n++) {
				System.out.println(m);
			}
		}

		System.out.println("====从小到大排序====");
		for (int m = 10; m > 0; m--) {
			for (int n = 0; n < buckets[m]; n++) {
				System.out.println(m);
			}
		}

	}
}
