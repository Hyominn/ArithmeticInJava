package com.op.algorithm;

/**
 * 平方数之和
 * 633. Sum of Square Numbers (Easy)
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 *
 * @Author: NZY
 * @Date: 2020/6/3 14:13
 */
public class SquareSum {
    public static void main(String[] args) {

    }

    /**
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
     * 输入: 5
     * 输出: True
     * 解释: 1 * 1 + 2 * 2 = 5
     *
     * 双指针
     * 时间复杂度：O(c^1/2)
     * 空间复杂度：O(1)
     */
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int powSum = i * i + j * j;
            if (powSum == c) {
                return true;
            } else if (powSum > c) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * 时间复杂度：O(c^1/2)
     * 空间复杂度：O(1)
     */
    public boolean judgeSquareSumBySqrt(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b)
                return true;
        }
        return false;
    }
}
