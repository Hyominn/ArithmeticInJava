package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 分隔字符串使同种字符出现在一起
 * <p>
 * 763. Partition Labels (Medium)
 * <p>
 * https://leetcode-cn.com/problems/partition-labels/description/
 *
 * @Author: NZY
 * @Date: 2020/6/11 11:26 下午
 */
public class PartitionLabels {
	public static void main(String[] args) {

	}

	/**
	 * 策略就是不断地选择从最左边起最小的区间。可以从第一个字母开始分析，假设第一个字母是 'a'，那么第一个区间一定包含最后一次出现的 'a'。但第一个出现的 'a' 和最后一个出现的 'a' 之间可能还有其他字母，这些字母会让区间变大。举个例子，在 "abccaddbeffe" 字符串中，第一个最小的区间是 "abccaddb"。
	 * 通过以上的分析，我们可以得出一个算法：对于遇到的每一个字母，去找这个字母最后一次出现的位置，用来更新当前的最小区间。
	 * 定义数组 last[char] 来表示字符 char 最后一次出现的下标。定义 anchor 和 j 来表示当前区间的首尾。如果遇到的字符最后一次出现的位置下标大于 j， 就让 j=last[c] 来拓展当前的区间。当遍历到了当前区间的末尾时(即 i==j )，把当前区间加入答案，同时将 start 设为 i+1 去找下一个区间。
	 * <p>
	 * Input: S = "ababcbacadefegdehijhklij"
	 * <p>
	 * Output: [9,7,8]
	 * <p>
	 * Explanation:
	 * The partition is "ababcbaca", "defegde", "hijhklij".
	 * This is a partition so that each letter appears in at most one part.
	 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	 */
	public List<Integer> partitionLabels(String S) {
		// 时间复杂度: O(N)，其中 N 为 S 的长度。
		// 空间复杂度: O(N)。

		// for循环通过相同的字符作为下标来覆盖 从而得到最后一个字符的位置
		int[] last = new int[26];
		for (int i = 0; i < S.length(); ++i) {
			last[S.charAt(i) - 'a'] = i;
		}

		int j = 0, anchor = 0;
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < S.length(); ++i) {
			j = Math.max(j, last[S.charAt(i) - 'a']);
			if (i == j) {
				ans.add(i - anchor + 1);
				anchor = i + 1;
			}
		}
		return ans;
	}
}
