package com.op.algorithm;


import javax.validation.constraints.NotNull;
import java.util.*;

import static com.op.util.Print.print;

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
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        // int[][] grids = {{1, 1, 0, 1}, {1, 0, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}};
        // int[][] grids = {{0, 1}, {1, 0}};
        int[][] grids = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        print(breadthFirstSearch.shortestPathBinaryMatrix(grids));
        
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
    public int shortestPathBinaryMatrix(@NotNull int[][] grids) {
        // 当矩阵第一个元素为 1 或者最后一个元素为 1 时，直接返回 -1
        int m = grids.length, n = grids[0].length;
        if (grids[0].length == 0 || grids[0][0] == 1 || grids[m - 1][n - 1] == 1) {
            return -1;
        }
        // 直接用 grid[i][j] 记录从起点到这个点的最短路径长。按照题意起点也有长度 1
        grids[0][0] = 1;
        // 创建队列用于保存每一格可以走的步
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        // 队列长度, c 要和队列做对比,知道何时到达队列长度
        int len = q.size();
        int c = 0;
        // 定义八个方向
        int[][] dirs = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}};
        // 定义最短路径长度
        int path = 1;
        while (!q.isEmpty()) {
            int[] data = q.poll();
            // x、y 代表当前的坐标 （x，y）
            int x = data[0];
            int y = data[1];
            // 如果走到最后一格，返回
            if (x == m - 1 && y == n - 1) {
                return path;
            }
            
            // 此处填充队列，将可走的步添加进队列
            for (int[] dir : dirs) {
                // x1，y1代表当前坐标移动后的坐标（x1，y1）
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                /*
                x1 >= 0 && y1 >= 0  保证是正方向向右下角移动
                x1 < m && y1 < n    保证数组移动未越界 未超出 grids
                grids[x1][y1] == 0  保证单元格未阻塞
                 */
                if (x1 >= 0 && y1 >= 0 && x1 < m && y1 < n && grids[x1][y1] == 0) {
                    q.add(new int[]{x1, y1});
                    // 将走过的单元格 grids[x1][y1] 设置为阻塞，防止重走
                    grids[x1][y1] = 1;
                }
            }
            
            // 判断是否存在走单元格
            c++;
            if (c == len) {
                c = 0;
                path++;
                len = q.size();
            }
        }
        return -1;
    }
    
    
    /**
     * AbstractMap.SimpleEntry<>()
     */
    public int shortestPathBinaryMatrixUseSimpleEntry(int[][] grids) {
        int m = grids.length, n = grids[0].length;
        if (grids[0].length == 0 || grids[0][0] == 1 || grids[m - 1][n - 1] == 1) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
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
    
    
    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * <p>
     * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     */
    public int numSquares(int n) {
        // 时间复杂度：O(√n)，在主循环中，我们检查数字是否可以分解为两个平方和，这需要 个迭代。在其他情况下，我们会在常数时间内进行检查。
        // 空间复杂度：O(1)，该算法消耗一个常量空间。
        ArrayList<Integer> squareNums = new ArrayList<Integer>();
        for (int i = 1; i * i <= n; ++i) {
            squareNums.add(i * i);
        }
        
        Set<Integer> queue = new HashSet<Integer>();
        queue.add(n);
        
        int level = 0;
        while (queue.size() > 0) {
            level += 1;
            Set<Integer> nextQueue = new HashSet<Integer>();
            for (Integer remainder : queue) {
                for (Integer square : squareNums) {
                    if (remainder.equals(square)) {
                        return level;
                    } else if (remainder < square) {
                        break;
                    } else {
                        nextQueue.add(remainder - square);
                    }
                }
            }
            queue = nextQueue;
        }
        return level;
    }
    
    
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * <p>
     * 利用广度优先搜索搜索从 beginWord 到 endWord 的路径。
     * 对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
     * 将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
     * 为了防止出现环，使用访问数组记录。
     * 当队列中有元素的时候，取出第一个元素，记为 current_word。
     * 找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
     * 从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
     * 对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
     * 最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
     * <p>
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * Output: 5
     * <p>
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 时间复杂度：O(M×N)，其中 M 是单词的长度 N 是单词表中单词的总数。找到所有的变换需要对每个单词做 M 次操作。同时，最坏情况下广度优先搜索也要访问所有的 N 个单词。
        // 空间复杂度：O(M×N)，要在 all_combo_dict 字典中记录每个单词的 M 个通用状态。访问数组的大小是 N。广搜队列最坏情况下需要存储 N 个单词。
        // Since all words are of same length.
        int l = beginWord.length();
        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>(16);
        wordList.forEach(
                word -> {
                    for (int i = 0; i < l; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, l);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });
        
        // Queue for BFS
        Queue<AbstractMap.SimpleEntry<String, Integer>> q = new LinkedList<>();
        q.add(new AbstractMap.SimpleEntry<>(beginWord, 1));
        
        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>(16);
        visited.put(beginWord, true);
        
        while (!q.isEmpty()) {
            AbstractMap.SimpleEntry<String, Integer> node = q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < l; i++) {
                
                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, l);
                
                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        q.add(new AbstractMap.SimpleEntry<>(adjacentWord, level + 1));
                    }
                }
            }
        }
        
        return 0;
    }
    
}
