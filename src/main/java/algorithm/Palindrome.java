package algorithm;

/**
 * 回文字符串
 * 125. Valid Palindrome (Easy)
 * https://leetcode-cn.com/problems/valid-palindrome/
 * 680. Valid Palindrome II (Easy)
 * https://leetcode-cn.com/problems/valid-palindrome-ii/description/
 *
 * @Author: NZY
 * @Date: 2020/6/3 16:02
 */
public class Palindrome {
	public static void main(String[] args) {

	}

	/**
	 * 定义收尾两个指针，当前面的指针向后移动，后面的指针向前移动。
	 * 遍历整个字符数组，判断元素是否满足回文字符串的要求如果不相等则返回false。
	 * <p>
	 * 使用Character.isLetterOrDigit() 来判断是否是数字和字母
	 * 使用toCharArray()来将字符串转化成char数组
	 * char类型不能用==来比较，需要转换成int
	 */
	public boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		char[] charArray = s.toCharArray();

		while (i < j) {
			// 将i和j指向第一个是字母和数字的位置
			while (!Character.isLetterOrDigit(charArray[i]) && i < j) {
				i++;
			}
			while (!Character.isLetterOrDigit(charArray[j]) && i < j) {
				j--;
			}
			// 如果不相等，就返回false
			if ((int) Character.toLowerCase(charArray[i]) != (int) Character.toLowerCase(charArray[j])) {
				return false;
			}
			// 将i和j向中间移动
			i++;
			j--;
		}
		return true;
	}


	/**
	 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
	 * <p>
	 * 输入: "abca"
	 * 输出: True
	 * 解释: 你可以删除c字符。
	 */
	public boolean isPalindrome2(String s) {
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
			}
		}
		return true;
	}

	private boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i++) != s.charAt(j--)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 另外一种写法
	 */
	public boolean isPalindromeIIOther(String s) {
		int low = 0, high = s.length() - 1;
		while (low < high) {
			char c1 = s.charAt(low), c2 = s.charAt(high);
			if (c1 == c2) {
				low++;
				high--;
			} else {
				boolean flag1 = true, flag2 = true;
				for (int i = low, j = high - 1; i < j; i++, j--) {
					char c3 = s.charAt(i), c4 = s.charAt(j);
					if (c3 != c4) {
						flag1 = false;
						break;
					}
				}
				for (int i = low + 1, j = high; i < j; i++, j--) {
					char c3 = s.charAt(i), c4 = s.charAt(j);
					if (c3 != c4) {
						flag2 = false;
						break;
					}
				}
				return flag1 || flag2;
			}
		}
		return true;
	}

}
