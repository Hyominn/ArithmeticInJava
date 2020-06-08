package com.op.demo;

import static com.op.util.Print.print;

/**
 * Int 和 Integer
 *
 * @Author: NZY
 * @Date: 2020/6/7 7:14 下午
 */
public class DiffIntInteger {
	public static void main(String[] args) {
		Integer integer = null;// Integer 默认值是null
		int i = 0; // int 默认值是0
		DiffIntInteger myDiff = new DiffIntInteger();
		myDiff.compareIntWithInteger();
	}

	void compareIntWithInteger() {
		// 由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的
		// 因为new生成的是两个对象，其内存地址不同
		Integer i = new Integer(100);
		Integer j = new Integer(100);
		print(i == j);// Number objects are compared using '==', not 'equals()'
		// Integer 变量和 int 变量比较时，只要两个变量的值是向等的，则结果为 true
		// 因为包装类 Integer 和基本数据类型 int 比较时，java 会自动拆包装为 int ，然后进行比较，实际上就变为两个 int 变量的比较
		int k = 100;
		print(i == k);// Condition 'i == j' is always 'true'


	}
}
