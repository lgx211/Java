package com.lgx.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 从两个集合之中取不同的元素
 *
 */
public class TakeDifferent {

	public static void main(String[] args) {
		/*
		 * 基本数据类型
		 */
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);

		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);

		/*
		 * String
		 */
		List<String> l3 = new ArrayList<String>();
		List<String> l4 = new ArrayList<String>();
		l3.add("aaa");
		l3.add("bbb");
		l3.add("ccc");
		l3.add("ddd");
		l4.add("aaa");
		l4.add("bbb");
		l4.add("ccc");
		l4.add("eee");
		l4.add("fff");

		/*
		 * Map
		 */
		List<String> l5 = new ArrayList<String>();
		List<String> l6 = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "aaa");
		
	}

}
