package com.op.demo;

import com.op.util.Result;

import static com.op.util.Print.print;

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
        // 静态语句块
        print("This is Static statement block");
    }
    
    public static void main(String[] args) {
        MyKeyWords myKeyWords = new MyKeyWords();
        
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
    
    void myStatic() {
        /*
        静态变量
        静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
        实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
         */
        
        
    }
    
}

