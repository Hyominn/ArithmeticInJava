package com.op.demo;

import static java.lang.Math.pow;

/**
 * @Author: NZY
 * @Date: 2020/6/7 7:13 下午
 */
public class Demo {
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
	}
}
