package algorithm;

import java.util.Arrays;

/**
 * Unique Paths
 * <p>
 * https://leetcode-cn.com/problems/unique-paths/
 * <p>
 * https://leetcode-cn.com/problems/unique-paths-ii/
 * <p>
 * https://leetcode-cn.com/problems/unique-paths-iii/
 *
 * @Author: NZY
 * @Date: 2020/7/6 11:40 下午
 */
public class UniquePaths {
	public static void main(String[] args) {

	}

	/**
	 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
	 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	 * 问总共有多少条不同的路径？
	 * <p>
	 * 输入: m = 3, n = 2
	 * <p>
	 * 输出: 3
	 * <p>
	 * 输入: m = 7, n = 3
	 * <p>
	 * 输出: 28
	 */
	public int uniquePaths(int m, int n) {
		/*
		1 <= m, n <= 100
		题目数据保证答案小于等于 2 * 10 ^ 9

		我们令 dp[i][j] 是到达 i, j 最多路径
		动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
		注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
		时间复杂度：O(m*n)
		空间复杂度：O(n)
		优化：因为我们每次只需要 dp[i-1][j],dp[i][j-1]
		我们实际上需要的元素是所求的那个元素头上（元素A）和左边的那个元素（元素B），
		因此实际上可以只使用一行数组来存储所有需要的元素
		因此一行数据初始化为所求元素的头上的那些元素，一个一个地求元素后，所求元素的当前值实际上是需要的元素A，
		左边的那个元素B实际上已经更新过了，所以可以直接优化为一行
		 */
		int[] arr = new int[n];
		Arrays.fill(arr, 1);
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				arr[j] += arr[j - 1];
			}
		}
		return arr[n - 1];
	}

	public int uniquePathsByArrangement(int m, int n) {
		//只跟第几行第几列有关，从m+n-2步中抽出m-1步
		long ans = 1;
		for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
			ans *= m + n - 2 - i;
			ans /= i + 1;
		}
		return (int) ans;
	}
}
