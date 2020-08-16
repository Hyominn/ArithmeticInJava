package algorithm;

/**
 * 子数组最大的和
 * <p>
 * 53. Maximum Subarray (Easy)
 * <p>
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * @Author: NZY
 * @Date: 2020/6/11 11:12 下午
 */
public class MaximumSubarray {
	public static void main(String[] args) {

	}

	/**
	 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int pre = 0, max = nums[0];
		for (int num : nums) {
			pre = Math.max(pre + num, num);
			max = Math.max(max, pre);
		}
		return max;
	}
}
