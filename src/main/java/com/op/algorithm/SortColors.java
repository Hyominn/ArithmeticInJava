package com.op.algorithm;

import java.util.ArrayList;

/**
 * 按颜色进行排序
 * 75. Sort Colors (Medium)
 * https://leetcode-cn.com/problems/sort-colors/description/
 *
 * @Author: NZY
 * @Date: 2020/6/6 8:26 下午
 */
public class SortColors {

	public static void main(String[] args) {
	}

	/**
	 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
	 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
	 * <p>
	 * Input: [2,0,2,1,1,0]
	 * Output: [0,0,1,1,2,2]
	 */
	public void sortColors(int[] nums) {
		int zero = -1, one = 0, two = nums.length;
		while (one < two) {
			if (nums[one] == 0) {
				swap(nums, ++zero, one++);
			} else if (nums[one] == 2) {
				swap(nums, --two, one);
			} else {
				++one;
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
}
