package foundation.demo;

import foundation.util.Print;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static foundation.util.Print.println;

/**
 * Java 8 新特性 Stream 类
 *
 * @Author: NZY
 * @Date: 2020/6/27 3:09 下午
 */
public class MyStream {
	public static void main(String[] args) {
		MyStream myStream = new MyStream();
		myStream.myStreamDemo();
	}

	/**
	 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
	 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
	 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
	 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
	 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
	 */
	void myStreamDemo() {
		/*
		Stream（流）是一个来自数据源的元素队列并支持聚合操作
		元素是特定类型的对象，形成一个队列。 Java中的 Stream 并不会存储元素，而是按需计算。
		数据源
			流的来源。 可以是集合，数组，I/O channel， 产生器 generator 等。
		聚合操作
			类似SQL语句一样的操作， 比如 filter, map, reduce, find, match, sorted 等。
		和以前的 Collection 操作不同， Stream 操作还有两个基础的特征：
		Pipelining:
			中间操作都会返回流对象本身。
			这样多个操作可以串联成一个管道， 如同流式风格（ fluent style ）。 这样做可以对操作进行优化， 比如延迟执行（ laziness ）和短路（ short-circuiting ）。
		内部迭代：
			以前对集合遍历都是通过 Iterator 或者 For-Each 的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。
			Stream 提供了内部迭代的方式， 通过访问者模式（ Visitor ）实现。
		 */


		/*
		stream() 为集合创建串行流。
		Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
		Collectors 可用于返回列表或字符串：
		 */
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", " ", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isBlank()).collect(Collectors.toList());
		println("筛选列表: " + filtered);
		String mergedString = strings.stream().filter(string -> !string.isBlank()).collect(Collectors.joining(", "));
		println("合并字符串: " + mergedString);
		println();

		/*
		parallelStream 使用 fork/join 并行方式来拆分任务和加速处理过程 是流并行处理程序的代替方法。
		parallelStream() 为集合创建并行流 这使得处理的过程会分而治之的同时 获取的流元素顺序不会是元数据顺序
		可以使用 forEachOrdered 避免该情况，但需要注意的是 forEachOrdered 以原数据顺序为标准
		sort() filter() 等中介操作，会试着平行化处理
		使用 parallelStream 来输出空字符串的数量：
		 */
		long count = strings.parallelStream().filter(String::isBlank).count();
		strings.stream().filter((string -> !string.isBlank())).forEach(Print::print);
		println();
		strings.parallelStream().filter((string -> !string.isBlank())).forEach(Print::print);
		println();
		strings.parallelStream().filter((string -> !string.isBlank())).forEachOrdered(Print::print);
		println();
		println(count);
		println();

		/*
		Stream 提供了新的方法 forEach 来迭代流中的每个数据。
		limit 方法用于获取指定数量的流。
		sorted 方法用于对流进行排序。
		使用 limit 方法与使用 sorted 方法打印 10 条有序数据：
		 */
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(Print::println);
		println();

		/*
		map 方法用于映射每个元素到对应的结果
		另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
		使用 map 输出了元素对应的平方数：
		 */
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> squaresList = numbers.stream().map(i -> i * i).collect(Collectors.toList());
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		println(numbers);
		println("列表元素对应的平方数 ：" + squaresList);
		println("列表中最大的数 : " + stats.getMax());
		println("列表中最小的数 : " + stats.getMin());
		println("所有数之和 : " + stats.getSum());
		println("平均数 : " + stats.getAverage());
		println();

		/*
		Stream 操作分类：
		中间操作 Intermediate operations：
			无状态 Stateless：
				unordered() filter map() mapToInt() mapToLong() mapToDouble() flatMap() flatMapToInt() flatMapToLong0 flatMapToDouble() peek()
			有状态 Stateful：
				distinct() sorted() limit() skip()
		最终操作 Terminal Operations
			非短路操作
				forEach() forEachOrdered() toArray() reduce() collect() max() min() count()
			短路操作 short-circuiting
				anyMatch() allMatch() noneMatch() findFirst() findAny()
		 */

	}
}
