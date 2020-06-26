package com.op.demo;

import java.util.Stack;

import static com.op.util.Print.print;

/**
 * check
 *
 * @Author: NZY
 * @Date: 2020/6/21 9:43 下午
 */
public class MyCheck {
	public static void main(String[] args) {

	}

	/**
	 * 使用栈检查是否为有效平衡符号
	 *
	 * @param str 待检查字符
	 * @return 是否平衡符号
	 */
	private static boolean isValidEx(String str) {
		Stack<Character> st = new Stack<Character>();

		if (str == null || str.length() == 0) {
			return true;
		}

		// 检查符号：() [] {}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ')') {
				if (!st.isEmpty() && st.peek() == '(') {
					st.pop();
				} else {
					return false;
				}
			} else if (str.charAt(i) == ']') {
				if (!st.isEmpty() && st.peek() == '[') {
					st.pop();
				} else {
					return false;
				}
			} else if (str.charAt(i) == '}') {
				if (!st.isEmpty() && st.peek() == '{') {
					st.pop();
				} else {
					return false;
				}
			} else {
				st.push(str.charAt(i));
			}

		}

		print(st);

		return st.isEmpty();
	}
}
