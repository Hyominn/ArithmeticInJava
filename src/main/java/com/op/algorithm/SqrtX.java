package com.op.algorithm;

import static com.op.util.Print.print;

/**
 * 求开方
 * <p>
 * 69. Sqrt(x) (Easy)
 * <p>
 * https://leetcode-cn.com/problems/sqrtx/description/
 *
 * @Author: NZY
 * @Date: 2020/6/13 10:30
 */
public class SqrtX {
    public static void main(String[] args) {
        SqrtX sqrtX = new SqrtX();
        print(sqrtX.mySqrt(8));
    }


    /**
     * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。
     * 可以利用二分查找在 0 ~ x 之间查找 sqrt。
     * 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。
     * 在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3
     * 因此最后的返回值应该为 h 而不是 l。
     * <p>
     * Input: 4 Output: 2
     * <p>
     * Input: 8 Output: 2
     * <p>
     * Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.
     */
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 0, h = x, m = 0, s = 0;
        while (l <= h) {
            m = l + (h - l) / 2;
            s = x / m;
            if (s < m) {
                h = m - 1;
            } else if (s > m) {
                l = m + 1;
            } else {
                return m; // s == m
            }
        }
        return h;
    }


    /**
     * Input : [1,2,3,4,5]
     * key : 3
     * <p>
     * return the index : 2
     */
    public int binarySearch(int[] nums, int key) {
        // 二分查找也称为折半查找，每次都能将查找区间减半，这种折半特性的算法时间复杂度为 O(logN)。
        int l = 0, m = 0, h = nums.length - 1;
        while (l < h) {
            //l + h 可能出现加法溢出，也就是说加法的结果大于整型能够表示的范围。
            // 但是 l 和 h 都为正数，因此 h - l 不会出现加法溢出问题。所以，最好使用第二种计算法方法。
            m = l + (h - l) / 2;
            if (nums[m] > key) {
                h = m - 1;
            } else if (nums[m] < key) {
                l = m + 1;
            } else {
                return m;
            }
        }
        // 循环退出时如果仍然没有查找到 key，那么表示查找失败。可以有两种返回值：
        // -1：以一个错误码表示没有查找到 key
        // l：将 key 插入到 nums 中的正确位置
        return -1;
    }


    /**
     * 二分查找可以有很多变种，实现变种要注意边界值的判断。
     * 例如在一个有重复元素的数组中查找 key 的最左位置的实现如下：
     */
    public int binarySearchLeft(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l < h) { // 循环条件为 l < h
            int m = l + (h - l) / 2;
            if (nums[m] >= key) {
                h = m; // h 的赋值表达式为 h = m
            } else {
                l = m + 1;
            }
        }
        return l; // 最后返回 l 而不是 -1
    }
}
