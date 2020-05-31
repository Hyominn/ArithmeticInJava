package com.op.algorithm;

/**
 * @Author: NZY
 * @Date: 2020/5/31 6:35 下午
 */
public class Fibonacci {
	public static void main(String[] args) {

	}

	public int fib(int N) {
		// 时间复杂度：O(2^N) 这是计算斐波那契数最慢的方法。因为它需要指数的时间。
		// 空间复杂度：O(N) 在堆栈中我们需要与 N 成正比的空间大小。该堆栈跟踪 fib(N) 的函数调用，随着堆栈的不断增长如果没有足够的内存则会导致 StackOverflowError。
		if (N <= 1) {
			return N;
		}
		return fib(N-1) + fib(N-2);
	}

}
