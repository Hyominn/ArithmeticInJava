package com.op.demo;

import static com.op.util.Print.print;

/**
 * @Author: NZY
 * @Date: 2020/6/13 14:35
 */
public class MyOperator {
    public static void main(String[] args) {
        MyOperator myOperator = new MyOperator();
        myOperator.myDivide();
        print();
        myOperator.myBalance();
        print();
        myOperator.myPrecision();
    }
    
    void myDivide() {
        int a = 10;
        int b = 3;
        double c = a / b;
        // c = (10/3) = (double)3 = 3.0 这里面涉及到一个低精度到高精度的隐式装换
        print(c);
        // 3.0
        print((double) a / b);
        // 3.3333333333333335
        // 减乘除计算，会先统一操作数的精度，其规则是先统一成精度高的数。
        // double型/int型、int型/double型，最后都转成double型/double型。
    }
    
    void myBalance() {
        // 取余运算符是 % 是一个双目运算符，它的操作数通常是正整数也可以是负数甚至是浮点数
        // 如果负数参与此运算，则结果的正负取决于前面一个数是整数还是负数。
        int a = 5, b = 2;
        print(a % b);
        // a - (a / b) * b 
        print(5 % 3);
        // 5 - (5 / 3) * 3 = 2 
        print(5 % -3);
        // 5 - (5 / -3) * -3 = 2 
        print(-5 % 3);
        // -5 - (-5 / 3) * 3 = -2 
        print(-5 % -3);
        // -5 - (-5 / -3) * -3 = -2 
        // 如果操作数中有浮点数则采用的规则为 
        double c = 5.5, d = 1.1;
        print(a % b);
        // a - (b * q) 这里 q = int (a / b) 
        print(5.2 % 3.1);
        // 5.2 - 1 * 3.1 = 2.1 
        print(5.2 % -3.1);
        // 5.2 - (-1) * (-3.1) = 2.1 
        print(-5.2 % 3.1);
        // -5.1 - (-1) * 3.1 = -2.1 
        print(-5.2 % -3.1);
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
        
        print(0.99999999f == 1f);
        // true
        
        print(0.9f == 1f);
        // false
        
        // 因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型向下转型为 short 类型。
        short s1 = 1;
        // s1 = s1 + 1;
        
        // 但是使用 += 或者 ++ 运算符会执行隐式类型转换。
        s1 += 1;
        s1++;
        print(s1);
        // 上面的语句相当于将 s1 + 1 的计算结果进行了向下转型：ImplicitConversion
        s1 = (short) (s1 + 1);
        print(s1);
        
        /*
         switch
         switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数几个值的类型进行等值判断
         如果值过于复杂，那么还是用 if 比较合适。
         Incompatible types. Found: 'long', required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum'
        */
    
    }
    
}
