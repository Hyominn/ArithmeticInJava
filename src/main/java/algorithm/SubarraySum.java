package algorithm;

import java.util.HashMap;

/**
 * @Author: NZY
 * @Date: 2020/5/28 15:44
 */
public class SubarraySum {
    public static void main(String[] arg) {
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
        int k = 7, result;
        result = subarraySum(nums, k);
        System.out.println(result);
    }

    //给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
    // 枚举
    public static int subarraySumByEnum(int[] nums, int k) {
        //时间复杂度：O(n^2)
        //空间复杂度：O(1)
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // 前缀和 + 哈希表优化
    // 扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
    public static int subarraySum(int[] nums, int k) {
        //时间复杂度：O(n)，其中 n 为数组的长度。
        // 我们遍历数组的时间复杂度为 O(n)，中间利用哈希表查询删除的复杂度均为 O(1)，因此总时间复杂度为 O(n)。
        //空间复杂度：O(n)，其中 n 为数组的长度。
        // 哈希表在最坏情况下可能有 n 个不同的键值，因此需要 O(n) 的空间复杂度。
        int count = 0, pre = 0;// 记录合适的连续字符串数量 记录前面数字相加之和
        // map记录前几个数字之和为K出现相同和的次数为V
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            pre += num;
            // 如果前面数字之和加上这个数字正好等于K（存在一个数字加上nums[i]结果为K 说明找到了
            if (map.containsKey(pre - k)) {
                // 累计
                count += map.get(pre - k);
            }
            // 计算新的和放入map
            map.put(pre, map.getOrDefault(pre, 0) + 1);//map 中存在pre的value则返回，否则取0的value
        }
        return count;
    }
}
