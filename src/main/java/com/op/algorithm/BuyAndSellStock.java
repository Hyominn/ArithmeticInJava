package com.op.algorithm;

import static com.op.util.Print.print;

/**
 * 买卖股票最大的收益
 * <p>
 * 121. Best Time to Buy and Sell Stock (Easy)
 * <p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/description/
 * <p>
 * 122. Best Time to Buy and Sell Stock II (Easy)
 * <p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 *
 * @Author: NZY
 * @Date: 2020/6/10 19:43
 */
public class BuyAndSellStock {
	public static void main(String[] args) {
		BuyAndSellStock buyAndSellStock = new BuyAndSellStock();
		int[] prices = {7, 1, 5, 3, 6, 4};
		print(buyAndSellStock.maxProfit(prices));
	}

	/**
	 * 一次股票交易包含买入和卖出，只进行一次交易，求最大收益。
	 * 只要记录前面的最小价格，将这个最小价格作为买入价格，然后将当前的价格作为售出价格，查看当前收益是不是最大收益。
	 * 注意：你不能在买入股票前卖出股票。
	 * <p>
	 * Input: [7,1,5,3,6,4]
	 * <p>
	 * Output: 5
	 * <p>
	 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.Not 7-1 = 6, as selling price needs to be larger than buying price.
	 * <p>
	 * Input: [7,6,4,3,1]
	 * <p>
	 * Output: 0
	 * <p>
	 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
	 */
	public int maxProfit(int[] prices) {
		// 时间复杂度：O(n)，只需要遍历一次。
		// 空间复杂度：O(1)，只使用了常数个变量。
		if (prices.length == 0) {
			return 0;
		}
		int min = prices[0], profit = 0;
		for (int price : prices) {
			if (price < min) {
				min = price;
			} else {
				profit = Math.max(profit, price - min);
			}
		}
		return profit;
	}

	/**
	 * 可以进行多次交易，多次交易之间不能交叉进行。
     * 可以理解成第一天买，能挣钱就第二天就卖。
	 * 对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。
	 * 而 d - a = (d - c) + (c - b) + (b - a) ，因此当访问到一个 prices[i] 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
	 * <p>
	 * Input: [7,1,5,3,6,4]
	 * <p>
	 * Output: 7
	 * <p>
	 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
	 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
	 */
	public int maxProfitII(int[] prices) {
		// 时间复杂度：O(n)，遍历一次。
		// 空间复杂度：O(1)，需要常量的空间。
		if (prices.length == 0) {
			return 0;
		}
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				profit += prices[i] - prices[i - 1];
			}
		}
		return profit;
	}

}
