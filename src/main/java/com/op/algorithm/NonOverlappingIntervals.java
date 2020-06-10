package com.op.algorithm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Comparator;

import static com.op.util.Print.print;

/**
 * 无重叠区间
 * <p>
 * 435. Non-overlapping Intervals (Medium)
 * <p>
 * https://leetcode-cn.com/problems/non-overlapping-intervals/description/
 * <p>
 * 用最少数量的箭引爆气球
 * <p>
 * 452. Minimum Number of Arrows to Burst Balloons (Medium)
 * <p>
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * @Author: NZY
 * @Date: 2020/6/9 16:45
 */
public class NonOverlappingIntervals {
	public static void main(String[] args) {
		NonOverlappingIntervals nonOverlappingIntervals = new NonOverlappingIntervals();

		int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
		print(nonOverlappingIntervals.eraseOverlapIntervals(intervals));

		int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
		// points = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
		print(nonOverlappingIntervals.findMinArrowShots(points));
	}

	/**
	 * 计算让一组区间不重叠所需要移除的区间个数。
	 * 先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
	 * 在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
	 * 按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
	 * 可以认为区间的终点总是大于它的起点。
	 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
	 * <p>
	 * Input: [ [1,2], [1,2], [1,2] ]
	 * Output: 2
	 * <p>
	 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
	 * <p>
	 * Input: [ [1,2], [2,3] ]
	 * Output: 0
	 * <p>
	 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
	 */
	public int eraseOverlapIntervals(@NotNull int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
		// 使用 lambda 表示式创建 Comparator 会导致算法运行时间过长，如果注重运行时间，可以修改为普通创建 Comparator 语句：
		// Arrays.sort(intervals, new Comparator<int[]>() {
		//     @Override
		//     public int compare(int[] o1, int[] o2) {
		//         return o1[1] - o2[1];
		//     }
		// });
		int cnt = 1, end = intervals[0][1];
		for (int[] interval : intervals) {
			if (interval[0] >= end) {
                end = interval[1];
                cnt++;
			}
		}
		return intervals.length - cnt;
	}


	/**
	 * 气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都被刺破。求解最小的投飞镖次数使所有气球都被刺破。
	 * 也是计算不重叠的区间个数，不过和 Non-overlapping Intervals 的区别在于，[1, 2] 和 [2, 3] 在本题中算是重叠区间。
	 * <p>
	 * Input:[[10,16], [2,8], [1,6], [7,12]]
	 * <p>
	 * Output:2
	 * <p>
	 * Explanation:
	 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
	 */
	public int findMinArrowShots(@NotNull int[][] points) {
	    if (points.length == 0) {
			return 0;
		}
		Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
		int cnt = 1, end = points[0][1];
		for (int[] point : points) {
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
			if (point[0] > end) {
                end = point[1];
                cnt++;
			}
		}
		return cnt;
	}
}
