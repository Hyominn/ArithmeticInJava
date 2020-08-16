package algorithm;

/**
 * KMP 串模式匹配算法
 *
 * @Author: NZY
 * @Date: 2020/7/10 11:39 下午
 */
public class KnuthMorrisPratt {

	/**
	 * 暴力匹配
	 * 思路：
	 * 1.如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
	 * 2.如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0 .相当于每次匹配失败时，i 回溯，j 被置为0。
	 * 时间复杂度为 O(m*n)（m、n分别为文本串和模式串的长度）。无需扩展存储空间。
	 *
	 * @param text    文本串
	 * @param pattern 模式串
	 * @return pattern返回在text中的位置
	 */
	public int bruteForceStringMatch(String text, String pattern) {
		if (null == text || pattern == null) {
			return -1;
		}

		int textLen = text.length();
		int patternLen = pattern.length();
		if (textLen < patternLen) {
			return -1;
		}

		char[] s = text.toCharArray();
		char[] p = pattern.toCharArray();
		int i = 0;
		int j = 0;
		while (i < textLen && j < patternLen) {
			if (s[i] == p[j]) {
				//如果当前字符匹配成功（即 S[i] == P[j] ），则 i++，j++
				i++;
				j++;
			} else {
				//如果失配（即 S[i]! = P[j] ），令 i = i - (j - 1)，j = 0
				i = i - (j - 1);
				j = 0;
			}
		}
		//匹配成功，返回模式串p在文本串s中的位置，否则返回-1
		if (j == patternLen) {
			return i - j;
		} else {
			return -1;
		}
	}

	/**
	 * KMP
	 */
	public int kmpStringMatch(String text, String pattern) {
		if (text == null  || pattern == null) {
			return -1;
		}

		int textLen = text.length();
		int patternLen = pattern.length();
		if (textLen < patternLen) {
			return -1;
		}

		char[] s = text.toCharArray();
		char[] p = pattern.toCharArray();
		int i = 0;
		int j = 0;
		while (i < textLen && j < patternLen) {
			if (s[i] == p[j]) {
				//如果当前字符匹配成功（即 S[i] == P[j] ），则 i++，j++
				i++;
				j++;
			} else {
				// i 不变 j 变为 next[j] todo
			}
		}
		//匹配成功，返回模式串p在文本串s中的位置，否则返回-1
		if (j == patternLen) {
			return i - j;
		} else {
			return -1;
		}
	}
}
