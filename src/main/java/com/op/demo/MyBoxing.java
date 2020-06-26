package com.op.demo;

import static com.op.util.Print.println;

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
        println();
        myBoxing.myDoubleDemo();
        println();
        myBoxing.myBooleanDemo();
        println();
        myBoxing.myLongDemo();
        println();
        myBoxing.myStringDemo();
        println();
        myBoxing.myDivide();
        println();
        myBoxing.myBalance();
        println();
        myBoxing.myPrecision();
        println();
        myBoxing.myPlusPlus();
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
        println(k == j);
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
        println(a == b);
        a = 127;
        b = 127;
        // a,b都是127，数值相等，且满足在IntegerCache取缓存的条件，所以对象相等.
        println(a == b);
        // 所以，包装类型间的相等判断应该用equals，而不是'=='
        
        Integer c = 1;
        Integer d = 2;
        Integer e = 3;
        println(e == (c + d));
        // c + d 包含了算术运算，因此会触发自动拆箱过程（会调用intValue方法），因此它们比较的是数值相等
    }
    
    void myDoubleDemo() {
        Double a = 100.0;
        Double b = 100.0;
        Double c = 200.0;
        Double d = 200.0;
        
        println(a == b);
        println(c == d);
        
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
        
        println(a == b);
        println(c == d);
        
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
        println(g == (a + b));
        println(g.equals(a + b));
        println(g.equals(a + h));
        println(g == (a + h));
        // true
        // false
        // true
        // true
    }
    
    void myStringDemo() {
        /*
        在 Java 8 中，String 内部使用 char 数组存储数据。
        public final class String
        implements java.io.Serializable, Comparable<String>, CharSequence {
        private final char value[];
        }
        在 Java 9之后，String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
        value 数组被声明为 final，这意味着 value 数组初始化之后就不能再引用其它数组。并且 String 内部没有改变 value 数组的方法，因此可以保证 String 不可变。
        String 不变的好处：
         1. 可以缓存 hash 值
            因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。
            不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。
         2. String Pool 的需要
            如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。
            只有 String 是不可变的，才可能使用 String Pool。
         3. 安全性
            String 经常作为参数，String 不可变性可以保证参数不可变。
            例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变
            改变 String 的那一方以为现在连接的是其它主机，而实际情况却不一定是。
         4. 线程安全
            String 不可变性天生具备线程安全，可以在多个线程中安全地使用。
            String 不可变 String 不可变，因此是线程安全的
            StringBuffer 和 StringBuilder 可变
            StringBuilder 不是线程安全的 StringBuffer 是线程安全的，内部使用 synchronized 进行同步
		 */
   
		/*
		字符串常量池（String Pool）保存着所有字符串字面量（literal strings），这些字面量在编译时期就确定。
		不仅如此，还可以使用 String 的 intern() 方法在运行过程将字符串添加到 String Pool 中。
		当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；
		否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。
		 */
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
		/*
		"abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；
		而使用 new 的方式会在堆中创建一个字符串对象。
		从 new String 的源码可以看到，在将一个字符串对象作为另一个字符串对象的构造函数参数时，并不会完全复制 value 数组内容，而是都会指向同一个 value 数组。
		 */
        
        String s7 = new String("test1");
        String s8 = s7 + "111";
        println(s7);
        println(s8);
        
        // 使用 StringBuilder 对变量进行修改的时候 会直接修改引用地址 导致引用的 sb1值改变
        StringBuilder sb1 = new StringBuilder("test2");
        StringBuilder sb2 = sb1.append("222");
        println(sb1);
        println(sb2);
    }
    
    void myDivide() {
        int a = 10;
        int b = 3;
        double c = a / b;
        // c = (10/3) = (double)3 = 3.0 这里面涉及到一个低精度到高精度的隐式装换
        println(c);
        // 3.0
        println((double) a / b);
        // 3.3333333333333335
        // 减乘除计算，会先统一操作数的精度，其规则是先统一成精度高的数。
        // double型/int型、int型/double型，最后都转成double型/double型。
    }
    
    void myBalance() {
        // 取余运算符是 % 是一个双目运算符，它的操作数通常是正整数也可以是负数甚至是浮点数
        // 如果负数参与此运算，则结果的正负取决于前面一个数是整数还是负数。
        int a = 5, b = 2;
        println(a % b);
        // a - (a / b) * b 
        println(5 % 3);
        // 5 - (5 / 3) * 3 = 2 
        println(5 % -3);
        // 5 - (5 / -3) * -3 = 2 
        println(-5 % 3);
        // -5 - (-5 / 3) * 3 = -2 
        println(-5 % -3);
        // -5 - (-5 / -3) * -3 = -2 
        // 如果操作数中有浮点数则采用的规则为 
        double c = 5.5, d = 1.1;
        println(a % b);
        // a - (b * q) 这里 q = int (a / b) 
        println(5.2 % 3.1);
        // 5.2 - 1 * 3.1 = 2.1 
        println(5.2 % -3.1);
        // 5.2 - (-1) * (-3.1) = 2.1 
        println(-5.2 % 3.1);
        // -5.1 - (-1) * 3.1 = -2.1 
        println(-5.2 % -3.1);
        // -5.1 - (-1) * (-3.1) = -2.1 
    }
    
    void myParameterPassing() {
        /*
         Java 的参数是以值传递的形式传入方法中，而不是引用传递。
         在方法中改变对象的字段值会改变原对象该字段值，因为引用的是同一个对象。
         但是在方法中将指针引用了其它对象，那么此时方法里和方法外的两个指针指向了不同的对象，在一个指针改变其所指向对象的内容对另一个指针所指向的对象没有影响。
        */
    }
    
    void myPrecision() {
        /*
         Java 不能隐式执行向下转型，因为这会使得精度降低。
         字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。
         float 的范围为 -2^128 ~ +2^127，也即 -3.40E+38 ~ +3.40E+38；double 的范围为 -2^1024 ~ +2^1023 ，也即 -1.79E+308 ~ +1.79E+308 。
         float 的精度为 7~8 位有效数字；double 的精度为16~17位。
        */
        
        // float f = 1.1;
        
        float f = 1.1f;
        
        println(0.99999999f == 1f);
        // true
        
        println(0.9f == 1f);
        // false
        
        // 因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型向下转型为 short 类型。
        short s1 = 1;
        // s1 = s1 + 1;
        
        // 但是使用 += 或者 ++ 运算符会执行隐式类型转换。
        s1 += 1;
        s1++;
        println(s1);
        // 上面的语句相当于将 s1 + 1 的计算结果进行了向下转型：ImplicitConversion
        s1 = (short) (s1 + 1);
        println(s1);
        
        /*
         switch
         switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数几个值的类型进行等值判断
         如果值过于复杂，那么还是用 if 比较合适。
         Incompatible types. Found: 'long', required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum'
        */
        
    }
    
    void myPlusPlus() {
        int i = 0;
        // 3 因为第一个 i 返回 1 ，第二个 i 在 1 的基础上加 1 返回 2 ，此式子相当于 1 + 2 = 3
        println((++i) + (++i));
        // 同理 6
        println((i++) + (++i));
    }
    
    /**
     * num + ""
     * <p>
     * String.valueOf(num)
     * <p>
     * Integer.toString(num)
     */
    void convertInt2String() {
        //int => String
        int num = 123456789, maxLoop = 100000;
        
        // num + ""
        //得到开始运行时系统时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < maxLoop; i++) {
            String str = num + "";
        }
        //得到结束运行时系统时间
        long end = System.currentTimeMillis();
        System.out.println("num + \"\" : " + (end - start));
        
        // String.valueOf(num)
        start = System.currentTimeMillis();
        for (int i = 0; i < maxLoop; i++) {
            String str = String.valueOf(num);
        }
        end = System.currentTimeMillis();
        System.out.println("String.valueOf(num) : " + (end - start));
        
        // Integer.toString(num)
        start = System.currentTimeMillis();
        for (int i = 0; i < maxLoop; i++) {
            String str = Integer.toString(num);
        }
        end = System.currentTimeMillis();
        System.out.println("Integer.toString(num) : " + (end - start));
    }
    
    /**
     * Integer.parseInt(str)
     */
    void convertString2Int() {
        //String => int
        String s = "123456789";
        int maxLoop = 100000;
        
        // Integer.parseInt(str)
        long start = System.currentTimeMillis();
        for (int i = 0; i < maxLoop; i++) {
            int n = Integer.parseInt(s);
        }
        long end = System.currentTimeMillis();
        System.out.println("Integer.parseInt(str) : " + (end - start));

        /*
        Integer.valueOf(str).intValue()
        这个方法没必要，valueOf 源码默认调用 parseInt
        public static Integer valueOf(String s) throws NumberFormatException {
            return Integer.valueOf(parseInt(s, 10));
        }
         */
        
    }
    
}
