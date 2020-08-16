package algorithm;

/**
 * 大于给定元素的最小元素
 * <p>
 * 744. Find Smallest Letter Greater Than Target (Easy)
 * <p>
 * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/description/
 *
 * @Author: NZY
 * @Date: 2020/6/13 17:03
 */
public class FindSmallestLetter {
    public static void main(String[] args) {

    }


    /**
     * 给定一个有序的字符数组 letters 和一个字符 target，要求找出 letters 中大于 target 的最小字符，如果找不到就返回第 1 个字符。
     * <p>
     * Input:letters = ["c", "f", "j"] target = "d"
     * <p>
     * Output: "f"
     * <p>
     * Input:letters = ["c", "f", "j"] target = "k"
     * <p>
     * Output: "c"
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, h = letters.length - 1, m = 0;
        while (l < h) {
            m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else if (letters[m] > target) {
                h = m - 1;
            }
        }
        // 循环之后 l 是最靠近 target 的字符
        return l < letters.length ? letters[l] : letters[0];
    }
}
