package com.op.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 为运算表达式设计优先级 分治
 * <p>
 * 241. Different Ways to Add Parentheses (Medium)
 * <p>
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/description/
 *
 * @Author: NZY
 * @Date: 2020/6/14 6:58 下午
 */
public class AddParentheses {
	public static void main(String[] args) {

	}


	/**
	 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
	 * <p>
	 * 分治算法，通过把问题分解为更小的子问题，先解决小问题，再把小问题的解合并起来的一种方法。通常，如果子问题的规模仍然很大，可以继续拆解成更小的任务，更进一步，实践中，大任务往往与子任务有相同的结构，以便于递归地进行任务拆解。
	 * <p>
	 * 以具体的例子来说明。现在一共9枚硬币，已知其中有一枚是假的，它比其他8枚都要轻。现在有一个天平，只称两次，能找到这枚硬币吗？我们倒着想，假如现在有两枚，称一次，这很简单，天平高的那一边的是假币，如果有三枚呢？那就有两种情况，如果天平平衡，那么未称的那枚是假币，如果天平不平衡，高的那边是假币。再往上一层，如果有9枚硬币，我们可以把它分为三堆，一堆3个，然后任选两堆放在天平上称，如果天平平衡，那么假币一定在没称的那一堆里，如果天平不平衡，那么假币就在天平高起的那一堆里。
	 * <p>
	 * Input: "2-1-1".
	 * <p>
	 * ((2-1)-1) = 0
	 * <p>
	 * (2-(1-1)) = 2
	 * <p>
	 * Output : [0, 2]
	 */
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> ways = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '+' || c == '-' || c == '*') {
				// 递归
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));
				for (int l : left) {
					for (int r : right) {
						switch (c) {
							case '+':
								ways.add(l + r);
								break;
							case '-':
								ways.add(l - r);
								break;
							case '*':
								ways.add(l * r);
								break;
							default:
								break;
						}
					}
				}
			}
		}
		if (ways.size() == 0) {
			ways.add(Integer.valueOf(input));
		}
		return ways;
	}

}
