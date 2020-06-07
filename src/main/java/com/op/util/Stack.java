package com.op.util;

import java.util.LinkedList;

/**
 * Stack
 *
 * @Author: NZY
 * @Date: 2020/6/7 4:10 下午
 */
public class Stack<T> {
	private LinkedList<T> storage = new LinkedList<T>();

	public void push(T v) {
		this.storage.addFirst(v);
	}

	public T peek() {
		return this.storage.getFirst();
	}

	public T pop() {
		return this.storage.removeFirst();
	}

	public boolean empty() {
		return this.storage.isEmpty();
	}

	public String toString() {
		return this.storage.toString();
	}
}
