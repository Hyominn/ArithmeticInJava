package com.op.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程安全
 *
 * @Author: NZY
 * @Date: 2020/5/25 20:01
 */
public class MyThread {
	/**
	 * 线程安全
	 */
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	Lock readLock = lock.readLock();
	Lock writeLock = lock.writeLock();
	private int x = 0;

	private void count() {
		writeLock.lock();
		try {
			x++;
		} finally {
			writeLock.unlock();
		}
	}

	private void print(int time) {
		readLock.lock();
		try {
			for (int i = 0; i < time; i++) {
				System.out.print(x + " ");
			}
			System.out.println();
		} finally {
			readLock.unlock();
		}
	}
}
