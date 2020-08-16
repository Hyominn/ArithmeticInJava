package algorithm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Kth Element 找到倒数第 k 个的元素。
 * 215. Kth Largest Element in an Array (Medium)
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * @Author: NZY
 * @Date: 2020/6/5 17:24
 */
public class KthElement {
	public static void main(String[] args) {
		// 假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度

	}

	/**
	 * 排序
	 * Input: [3,2,1,5,6,4] and k = 2
	 * Output: 5
	 */
	public int findKthLargestBySort(int[] nums, int k) {
		// 时间复杂度 O(NlogN)
		// 空间复杂度 O(1)
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	/**
	 * 用于求解 TopK Elements 问题，也就是 K 个最小元素的问题。可以维护一个大小为 K 的最小堆，最小堆中的元素就是最小元素。
	 * 最小堆需要使用大顶堆来实现，大顶堆表示堆顶元素是堆中最大元素。
	 * 这是因为我们要得到 k 个最小的元素，因此当遍历到一个新的元素时，需要知道这个新元素是否比堆中最大的元素更小，更小的话就把堆中最大元素去除，并将新元素添加到堆中。
	 * 所以我们需要很容易得到最大元素并移除最大元素，大顶堆就能很好满足这个要求。
	 * 堆也可以用于求解 Kth Element 问题，得到了大小为 k 的最小堆之后，因为使用了小顶堆来实现，因此堆顶元素就是第 k 大的元素。
	 * （1）堆是一颗完全二叉树；
	 * （2）小（大）顶堆中的每一个节点都不小于（不大于）它的父节点；
	 * （3）堆的插入、删除元素的时间复杂度都是O(log n)；
	 * （4）建堆的时间复杂度是O(n)；
	 * （5）堆排序的时间复杂度是O(nlog n)；
	 * （6）堆排序的空间复杂度是O(1)​；​
	 */
	public int findKthLargestByPriorityQueue(int[] nums, int k) {
		// 思路是创建一个小顶堆，将所有数组中的元素加入堆中，并保持堆的大小小于等于 k。
		// 这样，堆中就保留了前 k 个最大的元素。堆顶的元素就是正确答案。
		// 时间复杂度 O(NlogK)
		// 空间复杂度 O(K)

		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		// 小顶堆

		for (int val : nums) {
			pq.add(val);
			// 维护堆的大小为 K
			if (pq.size() > k) {
				pq.poll();
			}
		}

		return pq.peek();
	}

	/**
	 * 相对应的 可以用大顶堆来求数组中第k个最小元素
	 */
	public int findKthSmallestByPriorityQueue(@NotNull int[] nums, @NotNull int k) {
		// 大顶堆
		PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

		for (int val : nums) {
			pq.add(val);
			// 维护堆的大小为 K
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}

	/**
	 * Quickselect (霍尔选择算法)
	 * 可以使用快速选择的 partition() 进行实现。需要先打乱数组，否则最坏情况下时间复杂度为 O(N2)。
	 * 首先，我们选择一个枢轴，并在线性时间内定义其在排序数组中的位置。这可以通过 划分算法 的帮助来完成。
	 * 为了实现划分，沿着数组移动，将每个元素与枢轴进行比较，并将小于枢轴的所有元素移动到枢轴的左侧。
	 * 在输出的数组中，枢轴达到其合适位置。所有小于枢轴的元素都在其左侧，所有大于或等于的元素都在其右侧。
	 * 数组就被分成了两部分。如果是快速排序算法，会在这里递归地对两部分进行快速排序，时间复杂度为 O(NlogN)。
	 * 由于知道要找的第 N - k 小的元素在哪部分中，我们不需要对两部分都做处理，这样就将平均时间复杂度下降到 O(N)。
	 */
	public int findKthLargestByPartition(int[] nums, int k) {
		// 最终的算法十分直接了当 :
		// 随机选择一个枢轴。
		// 使用划分算法将枢轴放在数组中的合适位置 pos。将小于枢轴的元素移到左边，大于等于枢轴的元素移到右边。
		// 比较 pos 和 N - k 以决定在哪边继续递归处理。
		// 时间复杂度 O(N)
		// 空间复杂度 O(1)
		k = nums.length - k;
		int l = 0, h = nums.length - 1;
		while (l < h) {
			int j = partition(nums, l, h);
			if (j == k) {
				break;
			} else if (j < k) {
				l = j + 1;
			} else {
				h = j - 1;
			}
		}
		return nums[k];
	}

	private int partition(int[] a, int l, int h) {
		int i = l, j = h + 1;
		while (true) {
			while (a[++i] < a[l] && i < h) ;
			while (a[--j] > a[l] && j > l) ;
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, l, j);
		return j;
	}

	private void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
