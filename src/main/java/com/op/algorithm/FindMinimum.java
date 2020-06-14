package com.op.algorithm;

/**
 * 旋转数组的最小数字
 * <p>
 * 153. Find Minimum in Rotated Sorted Array (Medium)
 * <p>
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * @Author: NZY
 * @Date: 2020/6/14 4:02 下午
 */
public class FindMinimum {
	public static void main(String[] args) {

	}

	/**
	 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
	 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
	 * 请找出其中最小的元素。
	 * 你可以假设数组中不存在重复元素。
	 * 解题关键是检验数组是否被旋转，如果数组没有被旋转，是升序排列，就满足 last element > first element。
	 * <p>
	 * Input: [3,4,5,1,2]
	 * <p>
	 * Output: 1
	 */
	public int findMin(int[] nums) {
		int l = 0, h = nums.length - 1, m = 0;
		while (l < h) {
			m = l + (h - l) / 2;
			if (nums[m] > nums[h]) {
				l = m + 1;
			} else {
				h = m;
			}
		}
		return nums[l];
	}
}
