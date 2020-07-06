package com.op.demo;

import com.op.util.Result;

import static com.op.util.Print.println;

// 静态导包


/**
 * @Author: NZY
 * @Date: 2020/6/18 16:27
 */
public class MyKeyWords {
	/**
	 * 实例变量
	 */
	private int x;
	/**
	 * 静态变量
	 */
	private static int y;


	static {
		// 静态语句块 静态语句块在类初始化时运行一次。
		println("This is Static statement block");
	}

	/**
	 * 内部类
	 */
	class InnerClass {
	}

	/**
	 * 静态内部类
	 */
	static class StaticInnerClass {
		// 静态内部类不能访问外部类的非静态的变量和方法。

	}

	public static void main(String[] args) {
		MyKeyWords myKeyWords = new MyKeyWords();
    
        /*
        InnerClass innerClass = new InnerClass();
        'OuterClass.this' cannot be referenced from a static context
         */
		InnerClass innerClass = myKeyWords.new InnerClass();
		StaticInnerClass staticInnerClass = new StaticInnerClass();

		// int x = A.x;  // Non-static field 'x' cannot be referenced from a static context
		int x = myKeyWords.x;
		// int y = myKeyWords.y;
	}

	void myFinal() {
        /*
        修饰数据
        声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
        对于基本类型，final 使数值不变；
        对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
         */

		final int x = 1;
		// x = 2;  // cannot assign value to final variable 'x'
		final Result r = new Result(1, "a");
		// you can change r.code value
		r.code = 0;
        
        /*
        修饰方法
        声明方法不能被子类重写。
        private 方法隐式地被指定为 final，如果在子类中定义的方法和基类中的一个 private 方法签名相同，
        此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。
        
        修饰类
        声明类不允许被继承。
         */
	}

	static void myStatic() {
        /*
        静态变量
        静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，
                可以直接通过类名来访问它。静态变量在内存中只存在一份。
        实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
         */
        
        /*
        静态方法
        静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
        只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，因此这两个关键字与具体对象关联。
         */
		int a = y;
        /*
        int b = x;  Non-static field 'x' cannot be referenced from a static context
        int b = this.y; 'com.op.demo.MyKeyWords.this' cannot be referenced from a static context
         */
        
        /*
        静态语句块
        静态语句块在类初始化时运行一次。
         */
        
        /*
        静态内部类
        非静态内部类依赖于外部类的实例，也就是说需要先创建外部类实例，才能用这个实例去创建非静态内部类。而静态内部类不需要。
         */
        
        /*
        静态导包
        在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。
         */
        
        /*
        初始化顺序
        静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
        存在继承的情况下，初始化顺序为：
            父类（静态变量、静态语句块）
            子类（静态变量、静态语句块）
            父类（实例变量、普通语句块）
            父类（构造函数）
            子类（实例变量、普通语句块）
            子类（构造函数）
         */
	}



}

