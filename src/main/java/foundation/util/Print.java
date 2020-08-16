package foundation.util;

import java.io.PrintStream;
import java.util.List;
import java.util.function.Predicate;

/**
 * Print
 *
 * @Author: NZY
 * @Date: 2020/6/7 3:11 下午
 */
public class Print {
	public static void println(Object obj) {
		System.out.println(obj);
	}

	public static void println() {
		System.out.println();
	}
	
	public static void println(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.print(n + " ");
			}
		}
		System.out.println();
	}

	public static void print(Object obj) {
		System.out.print(obj);
	}

	public static PrintStream print(String format, Object... args) {
		return System.out.printf(format, args);
	}
}
