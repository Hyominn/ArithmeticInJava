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
    }


    void myDivide() {
        int a = 10;
        int b = 3;
        double c = a / b; // c = (10/3) = (double)3 = 3.0 这里面涉及到一个低精度到高精度的隐式装换
        print(c); // 3.0
        print((double) a / b); // 3.3333333333333335
        // 减乘除计算，会先统一操作数的精度，其规则是先统一成精度高的数。
        // double型/int型、int型/double型，最后都转成double型/double型。
    }


    void myBalance() {
        // 取余运算符是 % 是一个双目运算符，它的操作数通常是正整数也可以是负数甚至是浮点数
        // 如果负数参与此运算，则结果的正负取决于前面一个数是整数还是负数。
        int a = 5, b = 2;
        print(a % b); // a - (a / b) * b 
        print(5 % 3); // 5 - (5 / 3) * 3 = 2 
        print(5 % -3); // 5 - (5 / -3) * -3 = 2 
        print(-5 % 3); // -5 - (-5 / 3) * 3 = -2 
        print(-5 % -3); // -5 - (-5 / -3) * -3 = -2 
        // 如果操作数中有浮点数则采用的规则为 
        double c = 5.5, d = 1.1;
        print(a % b); // a - (b * q) 这里 q = int (a / b) 
        print(5.2 % 3.1); // 5.2 - 1 * 3.1 = 2.1 
        print(5.2 % -3.1); // 5.2 - (-1) * (-3.1) = 2.1 
        print(-5.2 % 3.1); // -5.1 - (-1) * 3.1 = -2.1 
        print(-5.2 % -3.1); // -5.1 - (-1) * (-3.1) = -2.1 
    }
}
