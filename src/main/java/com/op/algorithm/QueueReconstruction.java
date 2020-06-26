package com.op.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.op.util.Print.println;

/**
 * 根据身高和序号重组队列
 * <p>
 * 406. Queue Reconstruction by Height(Medium)
 * <p>
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/description/
 *
 * @Author: NZY
 * @Date: 2020/6/10 18:57
 */
public class QueueReconstruction {
    public static void main(String[] args) {
        QueueReconstruction queueReconstruction = new QueueReconstruction();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        println(Arrays.deepToString(people)); // java 打印二维数组 Arrays.deepToString()
        println(Arrays.deepToString(queueReconstruction.reconstructQueue(people)));
    }
    
    /**
     * 一个学生用两个分量 (h, k) 描述，h 表示身高，k 表示排在前面的有 k 个学生的身高比他高或者和他一样高。
     * 为了使插入操作不影响后续的操作，身高较高的学生应该先做插入操作，否则身高较小的学生原先正确插入的第 k 个位置可能会变成第 k+1 个位置。
     * 身高 h 降序、个数 k 值升序，然后将某个学生插入队列的第 k 个位置中。
     * <p>
     * Input:[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * <p>
     * Output:[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) {
            return new int[0][0];
        }
        // 身高 h 降序、个数 k 值升序
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[queue.size()][2]);
    }
}
