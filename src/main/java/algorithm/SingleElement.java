package algorithm;

import static foundation.util.Print.println;

/**
 * 有序数组中的单一元素
 * <p>
 * 540. Single Element in a Sorted Array (Medium)
 * <p>
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/description/
 *
 * @Author: NZY
 * @Date: 2020/6/13 11:19 下午
 */
public class SingleElement {
    public static void main(String[] args) {
        SingleElement singleElement = new SingleElement();
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};
        println(singleElement.singleNonDuplicate(nums1));
        println(singleElement.singleNonDuplicate(nums2));
    }
    
    
    /**
     * 一个有序数组只有一个数不出现两次，找出这个数。
     * 要求以 O(logN) 时间复杂度进行求解，因此不能遍历数组并进行异或操作来求解，这么做的时间复杂度为 O(N)。
     * 令 index 为 Single Element 在数组中的位置。在 index 之后，数组中原来存在的成对状态被改变。如果 m 为偶数，并且 m + 1 < index，那么 nums[m] == nums[m + 1]；m + 1 >= index，那么 nums[m] != nums[m + 1]。
     * 从上面的规律可以知道，如果 nums[m] == nums[m + 1]，那么 index 所在的数组位置为 [m + 2, h]，此时令 l = m + 2；如果 nums[m] != nums[m + 1]，那么 index 所在的数组位置为 [l, m]，此时令 h = m。
     * 因为 h 的赋值表达式为 h = m，那么循环条件也就只能使用 l < h 这种形式。
     * <p>
     * Input: [1, 1, 2, 3, 3, 4, 4, 8, 8]
     * <p>
     * Output: 2
     */
    public int singleNonDuplicate(int[] nums) {
        // 时间复杂度：O(log(n/2)}) = O(logn)。我们仅对元素的一半进行二分搜索。
        // 空间复杂度：O(1)，仅用了常数的空间。
        int l = 0, h = nums.length - 1, m = 0;
        while (l < h) {
            m = l + (h - l) / 2;
            if (m % 2 == 1) {
                // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
                m--;
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }
    
    
    /**
     * 我们首先将 lo 和 hi 指向数组首尾两个元素。然后进行二分搜索将数组搜索空间减半，直到找到单一元素或者仅剩一个元素为止。当搜索空间只剩一个元素，则该元素就是单个元素。
     * 在每个循环迭代中，我们确定 mid，变量 halvesAreEven = (hi - mid) % 2 == 0。 通过查看中间元素同一元素为哪一个（左侧子数组中的最后一个元素或右侧子数组中的第一个元素），我们可以通过变量 halvesAreEven 确定现在哪一侧元素个数为奇数，并更新 lo 和 hi。
     * 最难的部分是根据 mid 和 halvesAreEven 的值正确更新 lo 和 hi。
     */
    public int singleNonDuplicateAll(int[] nums) {
        // 时间复杂度：O(logn)。在每次循环迭代中，我们将搜索空间减少了一半。
        // 空间复杂度：O(1)，仅使用了常数空间。
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }
}
