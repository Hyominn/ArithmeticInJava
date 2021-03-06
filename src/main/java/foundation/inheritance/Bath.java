package foundation.inheritance;

import static foundation.util.Print.println;

/**
 * @Author: NZY
 * @Date: 2020/6/29 10:50 下午
 */
public interface Bath {

	/**
	 * undress
	 */
	void undress();

	/**
	 * Bath
	 */
	default void shower() {
		println("... ... bathing, No peeking!");
	}

	/**
	 * vest
	 */
	public void vest();


	/*
	接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。
	从 Java 8 开始，接口也可以拥有默认的方法实现，这是因为不支持默认方法的接口的维护成本太高了。在 Java 8 之前，如果一个接口想要添加新的方法，那么要修改所有实现了该接口的类，让它们都实现新增的方法。
	接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。
	接口的字段默认都是 static 和 final 的。
	 */

	/*
	从设计层面上看，
		抽象类提供了一种 IS-A 关系，需要满足里式替换原则，即子类对象必须能够替换掉所有父类对象。
		而接口更像是一种 LIKE-A 关系，它只是提供一种方法实现契约，并不要求接口和实现接口的类具有 IS-A 关系。
	从使用上来看，
		一个类可以实现多个接口，但是不能继承多个抽象类。
	接口的字段只能是 static 和 final 类型的，而抽象类的字段没有这种限制。
	接口的成员只能是 public 的，而抽象类的成员可以有多种访问权限。
	 */

	/*
	使用接口：
		需要让不相关的类都实现一个方法，例如不相关的类都可以实现 Comparable 接口中的 compareTo() 方法；
		需要使用多重继承。
	使用抽象类：
		需要在几个相关的类中共享代码。
		需要能控制继承来的成员的访问权限，而不是都为 public。
		需要继承非静态和非常量字段。

	在很多情况下，接口优先于抽象类。因为接口没有抽象类严格的类层次结构要求，可以灵活地为一个类添加行为。
	并且从 Java 8 开始，接口也可以有默认的方法实现，使得修改接口的成本也变的很低。
	 */
}
