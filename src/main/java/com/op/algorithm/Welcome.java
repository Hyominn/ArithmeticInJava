package com.op.algorithm;

import static java.lang.Math.pow;

/**
 * This is the first sample program
 *
 * @author niezhiyong
 * @version 2020-2-26 17:42:20
 * @since 0.0.1
 */
public class Welcome {
    public static final double PI = 3.14159265358979323846;

    //main 方法不对任何对象进行操作。事实上，在启动程序时还没有任何一个对象。静态的main 方法将执行并创建程序所需要的对象
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //String name = in.nextLine();
        String name = "Kris Nie";
        String greeting = "com.op.algorithm.Welcome to core Java";
        System.out.println(name); // is this too easy?
        System.out.println(greeting); // is this too easy?
        double salary;
        int vacationDays;
        long earthPopulation;
        boolean done;
        int x = 2, a = 10;
        double y = pow(x, a);
        x += 3.5;// 同（int)(x + 3.5) 运算完的值类型会强制与‘+=’运算符左边的类型一致
        String all = String.join(" / ", "S", "M", "L", "XL");// S / M / L / XL
        System.out.println(x);
        System.out.println(y);
        System.out.println(all);
        System.out.println();

        String s1 = "Ok";
        StringBuilder sb1 = new StringBuilder(s1);
        System.out.println(s1.hashCode() + " " + sb1.hashCode());//2556 1051754451
        String s2 = new String("Ok");
        StringBuilder sb2 = new StringBuilder(s2);
        System.out.println(s2.hashCode() + " " + sb2.hashCode());//2556 1732398722

        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

        System.out.println(cache.map.toString());
    }
}



