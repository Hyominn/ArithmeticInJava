package com.op.util;

/**
 * 浮点数比较
 *
 * @Author: NZY
 * @Date: 2020/6/29 10:05 下午
 */
public class DoubleComparer {
	/**
	 * 默认比较精度
	 */
	private static final double DEFAULT_DELTA = 0.000001;

	/**
	 * 比较2个double值是否相等（默认精度）
	 */
	public static boolean doubleCompare(double v1, double v2) {
		return doubleCompare(v1, v2, DEFAULT_DELTA);
	}

	/**
	 * 比较2个double值是否相等（指定精度）
	 */
	public static boolean doubleCompare(double v1, double v2, double delta) {
		return Double.compare(v1, v2) == 0 || doubleIsZero(v1 - v2, delta);
	}

	/**
	 * 判断指定double是否为0（默认精度）
	 */
	public static boolean doubleIsZero(double value) {
		return doubleIsZero(value, DEFAULT_DELTA);
	}

	/**
	 * 判断指定double是否为0（指定精度）
	 */
	public static boolean doubleIsZero(double value, double delta) {
		return Math.abs(value) <= delta;
	}
}
