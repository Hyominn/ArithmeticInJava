package com.op.algorithm;

import java.util.List;

/**
 * 最长子序列
 * 524. Longest Word in Dictionary through Deleting (Medium)
 * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/
 *
 * @Author: NZY
 * @Date: 2020/6/4 19:45
 */
public class LongestWordInDictionary {
    public static void main(String[] args) {

    }

    /**
     * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
     * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
     * <p>
     * 输入:
     * s = "abpcplea", d = ["ale","apple","monkey","plea"]
     * 输出:
     * "apple"
     */
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length(), l2 = target.length();
            if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {
                continue;
            }
            if (isSubstr(s, target)) {
                longestWord = target;
            }
        }
        return longestWord;
    }

    private boolean isSubstr(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }

}
