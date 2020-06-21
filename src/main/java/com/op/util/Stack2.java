package com.op.util;

import java.util.ArrayList;

/**
 * Stack 使用 Java 的 ArrayList 集合构造顺序栈
 * <p>
 * parameter：
 * <p>
 * elements         栈元素
 * <p>
 * length	        栈实际长度
 * <p>
 * method：
 * <p>
 * Stack()			构造栈
 * <p>
 * clearStack()	    清空栈
 * <p>
 * isEmpty()		判断栈空
 * <p>
 * getLength()		返回栈长
 * <p>
 * getTop()		    返回栈顶元素
 * <p>
 * push(T ele)		元素进栈
 * <p>
 * pop()			元素出栈
 *
 * @Author: NZY
 * @Date: 2020/6/21 4:41 下午
 */
public class Stack2<T> {
	/**
	 *
	 */
	private ArrayList<T> elements;
	int length;

	/**
	 * Constructor
	 */
	public Stack2() {
		elements = new ArrayList<>();
		length = 0;
	}

	/**
	 * Clear the Stack
	 */
	public void clearStack() {
		if (elements.size() > 0) {
			elements.subList(0, elements.size()).clear();
		}
		length = 0;
	}

	/**
	 * if the Stack is Empty, return true , else return false
	 *
	 * @return empty result
	 */
	public boolean isEmpty() {
		if (elements == null) {
			return true;
		}
		return length == 0;
	}

	/**
	 * get the Stack length
	 *
	 * @return length
	 */
	public int length() {
		return this.length;
	}

	/**
	 * get the top element of the stack.
	 * if exists, return true and give the value to T, else, return false
	 */
	public T peek() {
		if (elements == null && length == 0) {
			return null;
		} else {
			assert elements != null;
			return this.elements.get(length - 1);
		}
	}

	/**
	 * push a element(type T) to the stack
	 *
	 * @param ele 元数
	 */
	public void push(T ele) {
		this.length++;
		this.elements.add(ele);
	}

	/**
	 * pop a element(type T) from the stack
	 */
	public T pop() {
		T temp = elements.get(length - 1);
		elements.remove(length - 1);
		length--;
		return temp;
	}

	@Override
	public String toString() {
		return this.elements.toString();
	}
}
