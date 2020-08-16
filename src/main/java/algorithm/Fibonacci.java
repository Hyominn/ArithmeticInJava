package algorithm;

import javax.validation.constraints.NotNull;


import static foundation.util.Print.println;

/**
 * * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * * F(0) = 0,   F(1) = 1
 * * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * * 给定 N，计算 F(N)。
 *
 * @Author: NZY
 * @Date: 2020/5/31 6:35 下午
 */
public class Fibonacci {
	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		println(fibonacci.fib(45));
		println();
		println(fibonacci.fibByMemoizeUp(45));


	}

	/**
	 * recursion
	 */
	public int fib(int N) {
		// 时间复杂度：O(2^N) 这是计算斐波那契数最慢的方法。因为它需要指数的时间。
		// T(n)=T(n-1)+T(n-2)=2*T(n-2)+T(n-3)=…=F(n-1)+F(n-2)=F(n) 那么为了求第n个数,就要调用F(n)次函数.
		// 由于斐波那契数列是指数增长,所以该算法的时间复杂度也是指数增长,即O(2^n).
		// 空间复杂度：O(N) 在堆栈中我们需要与 N 成正比的空间大小。该堆栈跟踪 fib(N) 的函数调用，随着堆栈的不断增长如果没有足够的内存则会导致 StackOverflowError。
		if (N <= 1) {
			return N;
		}
		return fib(N - 1) + fib(N - 2);
	}

	/**
	 * 记忆化自底向上的方法
	 * 如果 N 小于等于 1，则返回 N。
	 * 迭代 N，将计算出的答案存储在数组中。
	 * 使用数组前面的两个斐波那契数计算当前的斐波那契数。
	 * 知道我们计算到 N，则返回它的斐波那契数。
	 */
	public int fibByMemoizeUp(int N) {
		// 时间复杂度：O(N)
		// 空间复杂度：O(N)
		if (N <= 1) {
			return N;
		}
		return memoizeUp(N);
	}

	public int memoizeUp(int N) {
		int[] cache = new int[N + 1];
		// [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
		cache[1] = 1;

		for (int i = 2; i <= N; i++) {
			cache[i] = cache[i - 1] + cache[i - 2];
		}
		return cache[N];
	}

	/*
	 * 记忆化自顶向下的方法
	 * 如果 N <= 1，则返回 N。
	 * 调用和返回 memoize(N)。
	 * 如果 N 对应的斐波那契数存在，则返回。
	 * 否则将计算 N 对应的斐波那契数列为 memoize(N-1) + memoize(N-2)。
	 */
	private Integer[] cache = new Integer[31];

	public int fibByMemoizeDown(int N) {
		// 时间复杂度：O(N)。
		// 空间复杂度：O(N)，内存中使用的堆栈大小。
		if (N <= 1) {
			return N;
		}
		cache[0] = 0;
		cache[1] = 1;
		return memoizeDown(N);
	}

	public int memoizeDown(int N) {
		if (cache[N] != null) {
			return cache[N];
		}
		cache[N] = memoizeDown(N - 1) + memoizeDown(N - 2);
		return memoizeDown(N);
	}

	/*
	 * iteration
	 * 若 N <= 1，则返回 N。
	 * 若 N == 2，则返回 fib(2-1) + fib(2-2) = 1。
	 * 使用迭代的方法，我们至少需要三个变量存储 fib(N), fib(N-1) 和 fib(N-2)。
	 * 预置初始值：
	 * current = 0。
	 * prev1 = 1，代表 fib(N-1)。
	 * prev2 = 1，代表 fib(N-2)
	 * 我们从 3 计算到 N；0，1，2对应的斐波那契数是预先计算。
	 * 设置 current = fib(N-1) + fib(N-2)，因为 current 代表的是当前计算的斐波那契数。
	 * 设置 prev2 = fib(N-1)。
	 * 设置 prev1 = current。
	 * 当我们到达 N+1，将退出循环并返回 current。
	 */
	public int fibByIteration(int N) {
		// 时间复杂度：O(N)。
		// 空间复杂度：O(1)，仅仅使用了 current，prev1，prev2。
		if (N <= 1) {
			return N;
		}
		if (N == 2) {
			return 1;
		}

		int current = 0;
		int prev1 = 1;
		int prev2 = 1;

		for (int i = 3; i <= N; i++) {
			current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return current;
	}

	/*
	 * 矩阵求幂
	 * 斐波那契数列矩阵方程
	 * 若 N 小于等一 1，则返回 N。
	 * 使用递归函数 matrixPower 计算给定矩阵 A 的幂。幂为 N-1，其中 N 是第 N 个 斐波那契数。
	 * matrixPower 函数将对 N/2 个斐波那契数进行操作。
	 * 在 matrixPower 中，调用 multiply 函数将两个矩阵相乘。
	 * 完成计算后，返回 A[0][0] 得到第 N 个斐波那契数。
	 * Matrix Fibonacci sequence equation
	 */
	int fibByEquation(int N) {
		// 时间复杂度：O(log N)。
		// 空间复杂度：O(log N)，matrixPower 函数递归时堆栈使用的空间。
		if (N <= 1) {
			return N;
		}
		int[][] A = new int[][]{{1, 1}, {1, 0}};
		matrixPower(A, N - 1);

		return A[0][0];
	}

	void matrixPower(int[][] A, int N) {
		if (N <= 1) {
			return;
		}
		matrixPower(A, N / 2);
		multiply(A, A);

		int[][] B = new int[][]{{1, 1}, {1, 0}};
		if (N % 2 != 0) {
			multiply(A, B);
		}
	}

	void multiply(@NotNull int[][] A, @NotNull int[][] B) {
		int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
		int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
		int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
		int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

		A[0][0] = x;
		A[0][1] = y;
		A[1][0] = z;
		A[1][1] = w;
	}

	/*
	 * 使用黄金分割比：φ = (1+5^(1/2))/2 ≈1.6180339887....
	 * 我们可以只能用常数时间和常数空间得到斐波那契数。
	 */
	public int fibByFormula(int N) {
		// 时间复杂度：O(1)。常数的时间复杂度，因为我们是基于 Binet 公式进行计算，没有使用循环或递归。
		// 空间复杂度：O(1)，存储黄金分割率所使用的空间。
		double goldenRatio = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(Math.pow(goldenRatio, N) / Math.sqrt(5));
	}
}
