package com.op.algorithm;


import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先搜索
 * <p>
 * Breadth-First Search
 * <p>
 * 广度优先搜索一层一层地进行遍历，每层遍历都是以上一层遍历的结果作为起点，遍历一个距离能访问到的所有节点。需要注意的是，遍历过的节点不能再次被遍历。
 * <p>
 * 每一层遍历的节点都与根节点距离相同。设 di 表示第 i 个节点与根节点的距离，推导出一个结论：对于先遍历的节点 i 与后遍历的节点 j，有 di <= dj。利用这个结论，可以求解最短路径等 最优解 问题：第一次遍历到目的节点，其所经过的路径为最短路径。应该注意的是，使用 BFS 只能求解无权图的最短路径，无权图是指从一个节点到另一个节点的代价都记为 1。
 * <p>
 * 在程序实现 BFS 时需要考虑以下问题：
 * <p>
 * 队列：用来存储每一轮遍历得到的节点；
 * <p>
 * 标记：对于遍历过的节点，应该将它标记，防止重复遍历。
 * <p>
 * 计算在网格中从原点到特定点的最短路径长度
 * <p>
 * 1091. Shortest Path in Binary Matrix(Medium)
 * <p>
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 * <p>
 * 组成整数的最小平方数数量
 * <p>
 * 279. Perfect Squares (Medium)
 * <p>
 * https://leetcode-cn.com/problems/perfect-squares/description/
 * <p>
 * 最短单词路径
 * <p>
 * 127. Word Ladder (Medium)
 * <p>
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * @Author: NZY
 * @Date: 2020/6/15 19:16
 */
public class BreadthFirstSearch {
	public static void main(String[] args) {

	}

	/**
	 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
	 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
	 * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
	 * C_1 位于 (0, 0)（即，值为 grid[0][0]）
	 * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
	 * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
	 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
	 * <p>
	 * 0 表示可以经过某个位置，求解从左上角到右下角的最短路径长度。
	 */
	public int shortestPathBinaryMatrix(int[][] grids) {
		// 当矩阵第一个元素为1或者最后一个元素为1时，直接返回-1
		if (grids == null || grids.length == 0 || grids[0].length == 0) {
			return -1;
		}
		int m = grids.length, n = grids[0].length;
		grids[0][0] = 1;
		// 创建队列用于保存每一格可以走的步
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{0, 0});
		// 队列长度,c要和队列做对比,知道何时到达队列长度
		int len = q.size();
		int c = 0;
		// 定义八个方向
		int[][] dir = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}};
		// 定义最短路径长度
		int path = 1;
		while (!q.isEmpty()) {
			int[] data = q.poll();
			// x、y代表当前的坐标（x，y）
			int x = data[0];
			int y = data[1];
			// 如果走到最后一格，返回
			if (x == m - 1 && y == n - 1) {
				return path;
			}
			// 此处填充队列，将可走的步添加进队列
			for (int[] d : dir) {
				// x1，y1代表当前坐标移动后的坐标（x1，y1）
				int x1 = x + d[0];
				int y1 = y + d[1];
				if (x1 >= 0 && y1 >= 0 && x1 < m && y1 < n && grids[x1][y1] == 0) {
					q.add(new int[]{x1, y1});
					grids[x1][y1] = 1;
				}
			}
			c++;
			if (c == len) {
				c = 0;
				path++;
				len = q.size();
			}
		}
		return -1;
	}

	public int shortestPathBinaryMatrixUseSimpleEntry(int[][] grids) {
		if (grids == null || grids.length == 0 || grids[0].length == 0) {
			return -1;
		}
		int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
		int m = grids.length, n = grids[0].length;
		Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
		queue.add(new AbstractMap.SimpleEntry<>(0, 0));
		int pathLength = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			pathLength++;
			while (size-- > 0) {
				AbstractMap.SimpleEntry<Integer, Integer> cur = queue.poll();
				assert cur != null;
				int cr = cur.getKey(), cc = cur.getValue();
				if (grids[cr][cc] == 1) {
					continue;
				}
				if (cr == m - 1 && cc == n - 1) {
					return pathLength;
				}
				grids[cr][cc] = 1;
				// 标记
				for (int[] d : direction) {
					int nr = cr + d[0], nc = cc + d[1];
					if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
						continue;
					}
					queue.add(new AbstractMap.SimpleEntry<>(nr, nc));
				}
			}
		}
		return -1;
	}


}
