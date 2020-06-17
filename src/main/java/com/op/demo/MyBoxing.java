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
		print();
		myBoxing.myStringDemo();
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

	/**
	 * 在 Java 8 中，String 内部使用 char 数组存储数据。
	 * public final class String
	 * implements java.io.Serializable, Comparable<String>, CharSequence {
	 * private final char value[];
	 * }
	 * 在 Java 9之后，String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
	 * value 数组被声明为 final，这意味着 value 数组初始化之后就不能再引用其它数组。并且 String 内部没有改变 value 数组的方法，因此可以保证 String 不可变。
	 * <p>
	 * String 不变的好处：
	 * <p>
	 * 1. 可以缓存 hash 值
	 * 因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。
	 * <p>
	 * 2. String Pool 的需要
	 * 如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。
	 * <p>
	 * 3. 安全性
	 * String 经常作为参数，String 不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变，改变 String 的那一方以为现在连接的是其它主机，而实际情况却不一定是。
	 * <p>
	 * 4. 线程安全
	 * String 不可变性天生具备线程安全，可以在多个线程中安全地使用。
	 * <p>
	 * String 不可变 String 不可变，因此是线程安全的
	 * <p>
	 * StringBuffer 和 StringBuilder 可变
	 * <p>
	 * StringBuilder 不是线程安全的 StringBuffer 是线程安全的，内部使用 synchronized 进行同步
	 */
	void myStringDemo() {
		// 字符串常量池（String Pool）保存着所有字符串字面量（literal strings），这些字面量在编译时期就确定。
		// 不仅如此，还可以使用 String 的 intern() 方法在运行过程将字符串添加到 String Pool 中。

		// 当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；
		// 否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。
		String s1 = new String("aaa");
		String s2 = new String("aaa");
		System.out.println(s1 == s2);
		// false
		String s3 = s1.intern();
		String s4 = s1.intern();
		System.out.println(s3 == s4);
		// true
		// intern() 首先把 s1 引用的字符串放到 String Pool 中，然后返回这个字符串引用。因此 s3 和 s4 引用的是同一个字符串。

		// 如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中。
		String s5 = "bbb";
		String s6 = "bbb";
		System.out.println(s5 == s6);
		// true

		String s = new String("abc");
		// "abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；
		// 而使用 new 的方式会在堆中创建一个字符串对象。
		// 从 new String 的源码可以看到，在将一个字符串对象作为另一个字符串对象的构造函数参数时，并不会完全复制 value 数组内容，而是都会指向同一个 value 数组。
	}
}
