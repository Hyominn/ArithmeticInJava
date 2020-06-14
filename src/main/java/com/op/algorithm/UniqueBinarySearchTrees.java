package com.op.algorithm;


import com.op.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 不同的二叉搜索树 分治 动态规划
 * <p>
 * 96. Unique Binary Search Trees
 * <p>
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * <p>
 * 95. Unique Binary Search Trees II
 * <p>
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 *
 * @Author: NZY
 * @Date: 2020/6/14 7:23 下午
 */
public class UniqueBinarySearchTrees {
	public static void main(String[] args) {

	}


	/**
	 * 给定一个有序序列 1 ... n，为了根据序列构建一棵二叉搜索树。我们可以遍历每个数字 i，将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。于是，我们可以递归地从子序列构建子树。
	 * 在上述方法中，由于根各自不同，每棵二叉树都保证是独特的。
	 * <p>
	 * Input: 3
	 * <p>
	 * Output: 5
	 */
	public int numTrees(int n) {
		int[] g = new int[n + 1];
		g[0] = 1;
		g[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				g[i] += g[j - 1] * g[i - j];
			}
		}
		return g[n];
	}


	/**
	 * Input: 3
	 * <p>
	 * Output:
	 * [
	 * [1,null,3,2],
	 * [3,2,null,1],
	 * [3,1,null,null,2],
	 * [2,1,3],
	 * [1,null,2,null,3]
	 * ]
	 * <p>
	 * Explanation:
	 * The above output corresponds to the 5 unique BST's shown below:
	 */
	public List<TreeNode> generateTrees(int n) {
		if (n < 1) {
			return new LinkedList<TreeNode>();
		}
		return generateSubtrees(1, n);
	}

	private List<TreeNode> generateSubtrees(int s, int e) {
		List<TreeNode> res = new LinkedList<TreeNode>();
		if (s > e) {
			res.add(null);
			return res;
		}
		for (int i = s; i <= e; ++i) {
			List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
			List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
			for (TreeNode left : leftSubtrees) {
				for (TreeNode right : rightSubtrees) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					res.add(root);
				}
			}
		}
		return res;
	}

}