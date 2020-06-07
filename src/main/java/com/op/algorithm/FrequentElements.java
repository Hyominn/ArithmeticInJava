package com.op.algorithm;

import java.io.Console;
import java.util.*;

/**
 * 出现频率最多的 k 个元素
 * 347. Top K Frequent Elements (Medium)
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 * <p>
 * 按照字符出现次数对字符串排序
 * 451. Sort Characters By Frequency (Medium)
 * https://leetcode-cn.com/problems/sort-characters-by-frequency/description/
 *
 * @Author: NZY
 * @Date: 2020/6/6 14:49
 */
public class FrequentElements {
	public static void main(String[] args) {
		FrequentElements f = new FrequentElements();
		int[] nums = new int[]{1, 1, 1, 2, 2, 3};
		int k = 2;
		System.out.println(f.topKFrequentByHeap(nums, k));
	}

	/**
	 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
	 * <p>
	 * 输入: nums = [1,1,1,2,2,3], k = 2
	 * 输出: [1,2]
	 */
	public List<Integer> topKFrequentByHeap(int[] nums, int k) {
		// 时间复杂度：O(Nlog(k))。Counter 方法的复杂度是 O(N)O(N)，建堆和输出的复杂度是 O(N \log(k))O(Nlog(k))。因此总复杂度为 O(N+Nlog(k))=O(Nlog(k))。
		// 空间复杂度：O(N)O(N)，存储哈希表的开销。

		// build hash map : character and how often it appears
		HashMap<Integer, Integer> count = new HashMap<>();
		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}

		// init heap 'the less frequent element first'
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));  // 大顶堆
		// keep k top frequent elements in the heap
		for (int n : count.keySet()) {
			heap.add(n);
			if (heap.size() > k)
				heap.poll();
		}

		// build output list
		List<Integer> top_k = new LinkedList<>();
		while (!heap.isEmpty())
			top_k.add(heap.poll());
		Collections.reverse(top_k);
		return top_k;
	}

	/**
	 * 桶排序
	 */
	public List<Integer> topKFrequentByBucketSort(int[] nums, int k) {
		Map<Integer, Integer> frequencyForNum = new HashMap<>();
		for (int num : nums) {
			frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
		}

		@SuppressWarnings("unchecked")
		List<Integer>[] buckets = new ArrayList[nums.length + 1];
		for (int key : frequencyForNum.keySet()) {
			int frequency = frequencyForNum.get(key);
			if (buckets[frequency] == null) {
				buckets[frequency] = new ArrayList<>();
			}
			buckets[frequency].add(key);
		}
		List<Integer> topK = new ArrayList<>();
		for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
			if (buckets[i] == null) {
				continue;
			}
			if (buckets[i].size() <= (k - topK.size())) {
				topK.addAll(buckets[i]);
			} else {
				topK.addAll(buckets[i].subList(0, k - topK.size()));
			}
		}
		return topK;
	}

	/**
	 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
	 * <p>
	 * Input:
	 * "tree"
	 * Output:
	 * "eert"
	 */
	public String frequencySort(String s) {
		Map<Character, Integer> frequencyForNum = new HashMap<>();
		for (char c : s.toCharArray())
			frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);

		@SuppressWarnings("unchecked")
		List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
		for (char c : frequencyForNum.keySet()) {
			int f = frequencyForNum.get(c);
			if (frequencyBucket[f] == null) {
				frequencyBucket[f] = new ArrayList<>();
			}
			frequencyBucket[f].add(c);
		}
		StringBuilder str = new StringBuilder();
		for (int i = frequencyBucket.length - 1; i >= 0; i--) {
			if (frequencyBucket[i] == null) {
				continue;
			}
			for (char c : frequencyBucket[i]) {
				for (int j = 0; j < i; j++) {
					str.append(c);
				}
			}
		}
		return str.toString();
	}
}
