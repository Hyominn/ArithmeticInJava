package com.op.algorithm;

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
    public int shortestPathBinaryMatrix(int[][] grid) {

    }

}
