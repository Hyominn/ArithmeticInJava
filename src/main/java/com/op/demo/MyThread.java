package com.op.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.op.util.Print.println;

/**
 * 多线程 高并发
 *
 * @Author: NZY
 * @Date: 2020/5/25 20:01
 */
public class MyThread {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		LockDemo lockDemo = new LockDemo();
		MyThread myThread = new MyThread();
		myThread.myThreadDemo();

	}

	void myThreadDemo() throws ExecutionException, InterruptedException {
		/*
		有三种使用线程的方法：
			实现 Runnable 接口；
			实现 Callable 接口；
			继承 Thread 类。
		实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。
		可以理解为任务是通过线程驱动从而执行的。
		 */
		runnableDemo();

		// 与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
		callableDemo();

		/*
		实现接口 VS 继承 Thread
		实现接口会更好一些，因为：
			Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；
			类可能只要求可执行就行，继承整个 Thread 类开销过大。
		 */
		threadDemo();


	}

	void runnableDemo() {
		RunnableDemo instance1 = new RunnableDemo("runnableThread instance1");
		Thread runnableThread = new Thread(instance1);
		runnableThread.start();

		RunnableDemo instance2 = new RunnableDemo("runnableThread instance2");
		instance2.start();

		RunnableDemo instance3 = new RunnableDemo("runnableThread instance3");
		instance3.start();
	}

	void callableDemo() throws ExecutionException, InterruptedException {
		CallableDemo mc = new CallableDemo();
		FutureTask<Integer> ft = new FutureTask<>(mc);
		Thread callableThread = new Thread(ft);
		callableThread.start();
		System.out.println(ft.get());
		println("callableThread");
	}

	void threadDemo() {
		ThreadDemo t = new ThreadDemo();
		t.start();
		println("ThreadDemo");
	}

	/**
	 * 线程安全
	 */
	static class LockDemo {
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

	/**
	 * 实现 Runnable 接口
	 */
	static class RunnableDemo implements Runnable {
		private Thread t;
		private String threadName;

		RunnableDemo(String name) {
			threadName = name;
			println("Creating " + threadName);
		}

		@Override
		public void run() {
			int loop = 4;

			println("Running " + threadName);
			try {
				for (int i = loop; i > 0; i--) {
					println("Thread: " + threadName + ", " + i);
					// 让线程睡眠一会
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				println("Thread " + threadName + " interrupted.");
			}
			println("Thread " + threadName + " exiting.");
		}

		public void start() {
			println("Starting " + threadName);
			if (t == null) {
				t = new Thread(this, threadName);
				t.start();
			}
		}
	}

	/**
	 * 实现 Callable 接口
	 */
	static class CallableDemo implements Callable<Integer> {
		@Override
		public Integer call() {
			return 123;
		}
	}

	/**
	 * 继承 Thread 类
	 */
	static class ThreadDemo extends Thread {
		@Override
		public void run() {
			println("继承 Thread 类");
		}
	}


}

