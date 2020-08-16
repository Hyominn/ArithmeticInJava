package foundation.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static foundation.util.Print.print;
import static foundation.util.Print.println;

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
		myThread.mySynchronizationDemo();
		myThread.myCooperationDemo();
		myThread.myJucDemo();
		myThread.myUnsafeDemo();
		myThread.myEliminateLocks();
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
		/*
		如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，
		那么调用线程的 interrupt() 方法就无法使线程提前结束。
		但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
		因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
		 */
		runnableThread.interrupt();

		RunnableDemo instance2 = new RunnableDemo("runnableThread instance2");
		instance2.start();
		instance2.shutdown();
		// instance2.

		/*
		Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
		主要有三种 Executor：
			CachedThreadPool：一个任务创建一个线程；
			FixedThreadPool：所有任务只能使用固定大小的线程；
			SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
		 */
		ExecutorService executorService = Executors.newScheduledThreadPool(2);
		Runnable r1 = new RunnableDemo("Executors");
		executorService.execute(r1);
		/*
		如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，
		通过调用该对象的 cancel(true) 方法就可以中断线程。
		 */
		Runnable r2 = new RunnableDemo("Future");
		Future<?> future = executorService.submit(r2);
		future.cancel(true);
		/*
		调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
		但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
		以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
		 */
		executorService.shutdown();
		executorService.shutdownNow();

	}

	void callableDemo() throws ExecutionException, InterruptedException {
		CallableDemo mc = new CallableDemo();
		// 返回值通过 Future 进行封装 Callable 的返回值
		FutureTask<Integer> ft = new FutureTask<>(mc);
		Thread callableThread = new Thread(ft);
		callableThread.start();
		System.out.println(ft.get());
		println("callableThread");
	}

	void threadDemo() {
		ThreadDemo t = new ThreadDemo();
		/*
		守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
		当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
		main() 属于非守护线程。
		在线程启动之前使用 setDaemon() 方法可以将一个线程设置为守护线程。
		 */
		t.setDaemon(true);
		t.start();
		println("ThreadDemo");
	}

	/**
	 * 互斥同步
	 */
	void mySynchronizationDemo() {
		// Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，第一个是 JVM 实现的 synchronized，而另一个是 JDK 实现的 ReentrantLock。
		SynchronizedDemo sync1 = new SynchronizedDemo();
		SynchronizedDemo sync2 = new SynchronizedDemo();
		ReentrantLockDemo lockDemo = new ReentrantLockDemo();
		ExecutorService executorService = Executors.newCachedThreadPool();
		// executorService.execute(sync1::func1);
		// executorService.execute(sync1::func1);
		// 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
		// executorService.execute(sync1::func1);
		// executorService.execute(sync2::func1);
		// 0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9
		// executorService.execute(sync1::func3);
		// executorService.execute(sync2::func3);
		// 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
		// executorService.execute(lockDemo::func);
		// executorService.execute(lockDemo::func);
		// 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
		executorService.shutdownNow();
		/*
		1. 锁的实现
			synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。
		2. 性能
			新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。
		3. 等待可中断
			当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。
			ReentrantLock 可中断，而 synchronized 不行。
		4. 公平锁
			公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。
			synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。
		5. 锁绑定多个条件
			一个 ReentrantLock 可以同时绑定多个 Condition 对象。

		除非需要使用 ReentrantLock 的高级功能，否则优先使用 synchronized。
		这是因为 synchronized 是 JVM 实现的一种锁机制，JVM 原生地支持它，而 ReentrantLock 不是所有的 JDK 版本都支持。
		并且使用 synchronized 不用担心没有释放锁而导致死锁问题，因为 JVM 会确保锁的释放。
		 */
	}

	/**
	 * 线程协作
	 */
	void myCooperationDemo() {
		/*
		join() 在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。

		调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
		它们都属于 Object 的一部分，而不属于 Thread。
		只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
		使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，
		那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
		 */
		ExecutorService executorService = Executors.newCachedThreadPool();
		WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
		executorService.execute(waitNotifyDemo::after);
		executorService.execute(waitNotifyDemo::before);
		// before
		// after
		/*
		wait() 和 sleep() 的区别
			wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
			wait() 会释放锁，sleep() 不会。
		 */
		AwaitSignalDemo awaitSignalDemo = new AwaitSignalDemo();
		executorService.execute(awaitSignalDemo::after);
		executorService.execute(awaitSignalDemo::before);
		/*
		await() signal() signalAll()
		java.foundation.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，
		可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。
		相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。
		使用 Lock 来获取一个 Condition 对象。
		 */
		executorService.shutdown();

	}

	void myJucDemo() throws InterruptedException, ExecutionException {
		// java.foundation.util.concurrent（J.U.C）大大提高了并发性能，AQS 被认为是 J.U.C 的核心。

		/*
		CountDownLatch
		用来控制一个或者多个线程等待多个线程。
		维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。
		 */
		final int totalThread = 10;
		CountDownLatch countDownLatch = new CountDownLatch(totalThread);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < totalThread; i++) {
			executorService.execute(() -> {
				print("run..");
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		println("end");
		// run..run..run..run..run..run..run..run..run..run..end

		/*
		CyclicBarrier
		用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。
		和 CountdownLatch 相似，都是通过维护计数器来实现的。线程执行 await() 方法之后计数器会减 1，并进行等待，
		直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。
		CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。
		CyclicBarrier 有两个构造函数，其中 parties 指示计数器的初始值，barrierAction 在所有线程都到达屏障的时候会执行一次。
		 */
		CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
		for (int i = 0; i < totalThread; i++) {
			executorService.execute(() -> {
				System.out.print("before..");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.print("after..");
			});
		}

		/*
		Semaphore todo: 进一步了解
		Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
		以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10。
		 */
		final int clientCount = 3;
		final int totalRequestCount = 10;
		Semaphore semaphore = new Semaphore(clientCount);
		for (int i = 0; i < totalRequestCount; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					System.out.print(semaphore.availablePermits() + " ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			});
		}
		// 1 1 0 1 1 1 0 1 0 0
		/*
		FutureTask 实现了 RunnableFuture 接口，该接口继承自 Runnable 和 Future 接口，
		这使得 FutureTask 既可以当做一个任务执行，也可以有返回值。
		FutureTask 可用于异步获取执行结果或取消执行任务的场景。
		当一个计算任务需要执行很长时间，那么就可以用 FutureTask 来封装这个任务，主线程在完成自己的任务之后再去获取结果。
		 */
		int loop = 100;
		FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
			int result = 0;
			for (int i = 0; i < loop; i++) {
				Thread.sleep(10);
				result += i;
			}
			return result;
		});

		Future<?> future = executorService.submit(futureTask);

		executorService.submit(() -> {
			println("other task is running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		println(futureTask.get());
		/*
		java.foundation.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现：
		FIFO 队列 ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
		优先级队列 ：PriorityBlockingQueue
		提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，直到队列有空闲位置。
		使用 BlockingQueue 实现生产者消费者问题 只有产品生产之后才能被消费
		 */
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		int putLoop1 = 2, takeLoop1 = 5, putLoop2 = 3;
		executorService.submit(() -> {
			for (int i = 0; i < putLoop1; i++) {
				try {
					queue.put("product");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				print("product..");
			}
		});
		executorService.submit(() -> {
			for (int i = 0; i < takeLoop1; i++) {
				try {
					String product = queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				print("consume..");
			}
		});
		executorService.submit(() -> {
			for (int i = 0; i < putLoop2; i++) {
				try {
					queue.put("product");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				print("product..");
			}
		});
		// put..put..take..take..put..put..take..put..take..take..
		executorService.shutdown();

		/*
		ForkJoin 主要用于并行计算中，和 MapReduce 原理类似，都是把大的计算任务拆分成多个小任务并行计算。
		ForkJoinPool 实现了工作窃取算法来提高 CPU 的利用率。
		每个线程都维护了一个双端队列，用来存储需要执行的任务。工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个任务来执行。
		窃取的任务必须是最晚的任务，避免和队列所属线程发生竞争。
		 */
		ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1, 10000);
		// ForkJoin 使用 ForkJoinPool 来启动，它是一个特殊的线程池，线程数量取决于 CPU 核数。
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> result = forkJoinPool.submit(forkJoinDemo);
		println(result.get());
		forkJoinPool.shutdown();
	}

	void myUnsafeDemo() throws InterruptedException {
		/*
		如果多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的。
		以下代码演示了 1000 个线程同时对 cnt 执行自增操作，操作结束之后它的值有可能小于 1000。
		 */
		final int threadSize = 1000;
		ThreadUnsafeDemo example1 = new ThreadUnsafeDemo();
		ThreadUnsafeDemo example2 = new ThreadUnsafeDemo();
		AtomicDemo atomicDemo = new AtomicDemo();
		final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < threadSize; i++) {
			executorService.execute(() -> {
				example1.add();
				countDownLatch.countDown();
			});
		}
		for (int i = 0; i < threadSize; i++) {
			executorService.execute(() -> {
				example2.syncAdd();
				countDownLatch.countDown();
			});
		}
		for (int i = 0; i < threadSize; i++) {
			executorService.execute(() -> {
				atomicDemo.add();
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		println(example1.get());
		// 994
		println(example2.syncGet());
		// 1000
		println(atomicDemo.get());
		// 1000
	}

	/**
	 * 锁消除
	 */
	void myEliminateLocks() {
		long tsStart = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			getString1("TestLockEliminate ", "Suffix");
		}
		System.out.println(" getString1 一共耗费：" + (System.currentTimeMillis() - tsStart) + " ms");

		tsStart = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			getString2("TestLockEliminate ", "Suffix");
		}
		System.out.println(" getString2 一共耗费：" + (System.currentTimeMillis() - tsStart) + " ms");

		tsStart = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			getString3("TestLockEliminate ", "Suffix");
		}
		System.out.println(" getString3 一共耗费：" + (System.currentTimeMillis() - tsStart) + " ms");

	}

	static String getString1(String s1, String s2) {
		StringBuffer sb = new StringBuffer();
		sb.append(s1);
		sb.append(s2);
		return sb.toString();
	}

	static String getString2(String s1, String s2) {
		return s1 + s2;
	}

	static String getString3(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		synchronized (sb) {
			sb.append(s1);
			sb.append(s2);
		}
		return sb.toString();
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
		volatile boolean shutdown = false;

		RunnableDemo(String name) {
			threadName = name;
			println("Creating " + threadName);
		}

		@Override
		public void run() {
			while (!Thread.interrupted() && !shutdown) {
				println("Running " + threadName);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				println("Thread " + threadName + " interrupted.");
			}
			/*
			对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。
			该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。
			 */
			Thread.yield();
			println("Thread " + threadName + " exiting.");
		}

		public void start() {
			println("Starting " + threadName);
			if (t == null) {
				t = new Thread(this, threadName);
				t.start();
			}
		}

		public void shutdown() {
			this.shutdown = true;
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
			try {
				/*
				Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。
				sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。
				线程中抛出的其它异常也同样需要在本地进行处理。
				 */
				Thread.sleep(2000);
				println(" 继承 Thread 类 Thread run");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 同步 synchronized
	 */
	static class SynchronizedDemo {
		private final int loop = 10;

		/**
		 * 使用 synchronized 同步一个代码块
		 * 作用于同一个对象
		 */
		public void func1() {
			synchronized (this) {
				for (int i = 0; i < loop; i++) {
					print(i + " ");
				}
				println();
			}
		}

		/**
		 * 使用 synchronized 同步一个方法
		 * 它和同步代码块一样，作用于同一个对象。
		 */
		public synchronized void func2() {
			for (int i = 0; i < loop; i++) {
				print(i + " ");
			}
			println();
		}

		/**
		 * 使用 synchronized 同步一个代码块
		 * 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
		 */
		public void func3() {
			synchronized (SynchronizedDemo.class) {
				for (int i = 0; i < loop; i++) {
					System.out.print(i + " ");
				}
				println();
			}
		}

		/**
		 * 可以使用一下方法 同步一个静态方法
		 * 作用于整个类
		 */
		public synchronized static void fcun4() {
			int loop = 10;
			for (int i = 0; i < loop; i++) {
				System.out.print(i + " ");
			}
			println();
		}
	}

	/**
	 * ReentrantLock
	 */
	static class ReentrantLockDemo {
		private final Lock lock = new ReentrantLock();

		public void func() {
			lock.lock();
			try {
				int loop = 10;
				for (int i = 0; i < loop; i++) {
					System.out.print(i + " ");
				}
			} finally {
				// 确保释放锁，从而避免发生死锁。
				lock.unlock();
			}
		}
	}

	static class WaitNotifyDemo {
		public synchronized void before() {
			System.out.println("before");
			notifyAll();
		}

		public synchronized void after() {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("after");
		}
	}

	static class AwaitSignalDemo {
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public void before() {
			lock.lock();
			try {
				println("before");
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		}

		public void after() {
			lock.lock();
			try {
				condition.await();
				println("after");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	static class ForkJoinDemo extends RecursiveTask<Integer> {

		private final int threshold = 5;
		private int first;
		private int last;

		public ForkJoinDemo(int first, int last) {
			this.first = first;
			this.last = last;
		}

		@Override
		protected Integer compute() {
			int result = 0;
			if (last - first <= threshold) {
				// 任务足够小则直接计算
				for (int i = first; i <= last; i++) {
					result += i;
				}
			} else {
				// 拆分成小任务
				int middle = first + (last - first) / 2;
				ForkJoinDemo leftTask = new ForkJoinDemo(first, middle);
				ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, last);
				leftTask.fork();
				rightTask.fork();
				result = leftTask.join() + rightTask.join();
			}
			return result;
		}
	}

	/**
	 * 线程不安全示例
	 */
	static class ThreadUnsafeDemo {
		private int cnt = 0;

		public void add() {
			cnt++;
		}

		public int get() {
			return cnt;
		}

		/**
		 * 使用 synchronized 实现线程安全
		 */
		public synchronized void syncAdd() {
			cnt++;
		}

		/**
		 * 使用 synchronized 实现线程安全
		 */
		public synchronized int syncGet() {
			return cnt;
		}
	}

	/**
	 * 使用 AtomicInteger 实现线程安全
	 */
	static class AtomicDemo {
		// 使用 AtomicInteger 重写之前线程不安全的代码之后得到\线程安全实现
		private final AtomicInteger cnt = new AtomicInteger();

		public void add() {
			// final 修饰的 AtomicInteger 可以被修改，由此可以看出 AtomicInteger 属于引用类型，incrementAndGet 修改的是所引用的对象
			cnt.incrementAndGet();
		}

		public int get() {
			return cnt.get();
		}
	}

}

