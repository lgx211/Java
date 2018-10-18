package com.lgx.time;

/**
 * 计算某方法耗时多少，毫秒级别
 */
public class TimeConsuming {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// 执行方法
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
		long endTime = System.currentTimeMillis();
		long excTime = endTime - startTime;
		System.out.println("执行时间：" + excTime + "ms");

	}

}
