package foundation.demo;

import java.util.Optional;

import static foundation.util.Print.println;

/**
 * Java 8 新特性 Optional 类
 * <p>
 * 用以解决 NPE 问题
 *
 * @Author: NZY
 * @Date: 2020/6/27 12:15 下午
 */
public class MyOptional {
	public static void main(String[] args) {
		MyOptional myOptional = new MyOptional();
		myOptional.myOptionalDemo();

	}

	/**
	 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
	 * <p>
	 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
	 * <p>
	 * Optional 类的引入很好的解决空指针异常。
	 */
	void myOptionalDemo() {
		Integer a = null;
		Integer b = 10;
		println(sum(a, b));
	}

	Integer sum(Integer a, Integer b) {
		// Optional.isPresent - 判断值是否存在
		println("第一个参数值存在: " + Optional.ofNullable(a).isPresent());
		println("第二个参数值存在: " + Optional.of(b).isPresent());

		/*
		Optional.ofNullable - 允许传递为 null 参数 并返回一个空的 Optional
		Optional.orElse - 如果值存在，返回它，否则返回默认值
		 */
		a = Optional.ofNullable(a).orElse(0);

		/*
		Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
		Optional.get - 获取值，值需要存在
		 */
		b = Optional.of(b).get();

		return a + b;
	}
}
