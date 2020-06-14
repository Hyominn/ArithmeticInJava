package com.op.demo;

import static com.op.util.Print.print;

/**
 * Int 和 Integer
 *
 * @Author: NZY
 * @Date: 2020/6/7 7:14 下午
 */
public class MyBoxing {
	public static void main(String[] args) {
		MyBoxing myBoxing = new MyBoxing();
		// 基本类型对应的缓冲池如下：
		// boolean values true and false
		// all byte values
		// short values between -128 and 127
		// int values between -128 and 127
		// char in the range \u0000 to \u007F
		myBoxing.myIntegerDemo();
		print();
		myBoxing.myDoubleDemo();
		print();
		myBoxing.myBooleanDemo();
		print();
		myBoxing.myLongDemo();
	}

	void myIntegerDemo() {
		// 谈谈Integer i = new Integer(xxx)和Integer i =xxx;这两种方式的区别。
		// 第一种方式不会触发自动装箱的过程；而第二种方式会触发；
		// 在执行效率和资源占用上的区别。第二种方式的执行效率和资源占用在一般性情况下要优于第一种情况（注意这并不是绝对的）。

		// 装箱
		Integer boxing = 10;
		// 拆箱
		int unboxing = boxing;
		// 在装箱的时候自动调用的是Integer的valueOf(int)方法。而在拆箱的时候自动调用的是Integer的intValue方法。

		// Integer 默认值是null
		Integer integer = null;
		// int 默认值是0
		int i = 0;

		Integer j = 100;
		int k = 100;
		// Integer 变量和 int 变量比较时，只要两个变量的值是向等的
		print(k == j);
		// 结果为 true Condition 'k == j' is always 'true'
		// 因为包装类 Integer 和基本数据类型 int 比较时，java 会自动拆包装为 int ，然后进行比较，实际上就变为两个 int 变量的比较

		// 非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。
		// 因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同

		// 两个new实例化出来的Integer变量比较，结果为false
		// 当new一个Integer时，实际上是生成一个指针,指向此对象,两次new Integer生成的是两个对象，对象储存在堆当中，其内存地址不同，所以两个new出来的Integer变量不等。

		// 对于两个非new生成的Integer对象，进行比较时
		Integer a = 128;
		Integer b = 128;
		// java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)
		// java API 中对 Integer 类型的 valueOf 的定义如下：
		// public static Integer valueOf(int i){
		//     assert IntegerCache.high >= 127;
		//     if (i >= IntegerCache.low && i <= IntegerCache.high){
		//         return IntegerCache.cache[i + (-IntegerCache.low)];
		//     }
		//     return new Integer(i);
		// }
		// 当自动装箱后生成的Integer的对象，其值 -128<= x <= 127 时，这个对象会直接取缓存IntegerCache中的对应的对象，生成的当然也是个对象
		// a 与 b 都是128，不会再 IntegerCache 取缓存对象 比较返回 false
		print(a == b);
		a = 127;
		b = 127;
		// a,b都是127，数值相等，且满足在IntegerCache取缓存的条件，所以对象相等.
		print(a == b);
		// 所以，包装类型间的相等判断应该用equals，而不是'=='

		Integer c = 1;
		Integer d = 2;
		Integer e = 3;
		print(e == (c + d));
		// c + d 包含了算术运算，因此会触发自动拆箱过程（会调用intValue方法），因此它们比较的是数值相等
	}

	void myDoubleDemo() {
		Double a = 100.0;
		Double b = 100.0;
		Double c = 200.0;
		Double d = 200.0;

		print(a == b);
		print(c == d);

		// Double.valueOf() 源码为
		// public static Double valueOf(double d) {
		// 	return new Double(d);
		// }
		// 所以 未 new 的 Double 变量比较 都返回 false
	}

	void myBooleanDemo() {
		Boolean a = false;
		Boolean b = false;
		Boolean c = true;
		Boolean d = true;

		print(a == b);
		print(c == d);

		// Boolean 的 valueOf 方法的源码：
		// public static Boolean valueOf(boolean b) {
		// 	return (b ? TRUE : FALSE);
		// }
		// TRUE 和 FALSE 是在Boolean中定义了2个静态成员属性：
		// public static final Boolean TRUE = new Boolean(true);
		// public static final Boolean FALSE = new Boolean(false);
	}

	void myLongDemo() {
		Integer a = 1;
		Integer b = 2;
		Long g = 3L;
		Long h = 2L;
		print(g == (a + b));
		print(g.equals(a + b));
		print(g.equals(a + h));
		print(g == (a + h));
		// true
		// false
		// true
		// true
	}
}
