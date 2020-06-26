package com.op.algorithm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.op.util.Print.println;

/**
 * 查找区间
 * <p>
 * 34. Find First and Last Position of Element in Sorted Array
 * <p>
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @Author: NZY
 * @Date: 2020/6/14 4:24 下午
 */
public class FindFirstAndLastPosition {
    public static void main(String[] args) {
        FindFirstAndLastPosition findFirstAndLastPosition = new FindFirstAndLastPosition();
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        println(Arrays.toString(findFirstAndLastPosition.searchRange(nums, target)));
    }
    
    /**
     * 给定一个有序数组 nums 和一个目标 target，要求找到 target 在 nums 中的第一个位置和最后一个位置。
     * 可以用二分查找找出第一个位置和最后一个位置，但是寻找的方法有所不同，需要实现两个二分查找。
     * 我们将寻找 target 最后一个位置，转换成寻找比 target 大的第一个位置，再往前移动一个位置。这样我们只需要实现一个二分查找代码即可。
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 8
     * <p>
     * Output: [3,4]
     * <p>
     * Input: nums = [5,7,7,8,8,10], target = 6
     * <p>
     * Output: [-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target, true);
        int last = findFirst(nums, target, false) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }
    
    private int findFirst(@NotNull int[] nums, int target, boolean left) {
        int l = 0, m = 0, h = nums.length;
        // 注意 h 的初始值
        while (l < h) {
            m = l + (h - l) / 2;
            // 通过参数 left 判断是否查找当前 nums[m] == target 的数组
            boolean existed = nums[m] > target || (left && (nums[m] == target));
            if (existed) {
                // 向左
                h = m;
            } else {
                // 向右
                l = m + 1;
            }
        }
        return l;
    }
}
