package com.op.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * twoSum
 *
 * @Author: NZY
 * @Date: 2020/6/2 19:22
 */
public class TwoSum {
    public static void main(String[] args) {

    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        //时间复杂度：O(n) 只遍历了包含有 nn 个元素的列表一次。在表中进行的每次查找只花费 O(1)O(1) 的时间。
        //空间复杂度：O(n) 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 nn 个元素。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * <p>
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     */
    public int[] twoSumII(int[] numbers, int target) {
        // 使用两个指针，初始分别位于第一个元素和最后一个元素位置，比较这两个元素之和与目标值的大小。
        // 如果和等于目标值，我们发现了这个唯一解。如果比目标值小，将较小元素指针增加一。如果比目标值大，我们将较大指针减小一。
        // 移动指针后重复上述比较知道找到答案。
        // 假设 [... , a, b, c, ... , d, e, f, …][...,a,b,c,...,d,e,f,…] 是已经升序排列的输入数组，
        // 并且元素 b, eb,e 是唯一解。
        // 从左到右移动较小指针，从右到左移动较大指针，总有某个时刻存在一个指针移动到 bb 或 ee 的位置。
        // 假设小指针县移动到了元素 bb ，这是两个元素的和一定比目标值大，向左移动较大指针直至获得结果。
        // 时间复杂度：O(n) 每个元素最多被访问一次，共有 nn 个元素。
        // 空间复杂度：O(1) 只是用了两个指针。

        int low = 0;
        int high = numbers.length - 1;
        while (numbers[low] + numbers[high] != target) {
            if (numbers[low] + numbers[high] < target) {
                low++;
            } else if (numbers[low] + numbers[high] > target) {
                high--;
            }
        }
        return new int[]{low + 1, high + 1};
    }
}
