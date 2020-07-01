package com.op.demo;


import com.op.util.Stack2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.op.util.Print.print;
import static com.op.util.Print.println;

/**
 * 持有对象
 *
 * @Author: NZY
 * @Date: 2020/6/6 9:07 下午
 */
public class MyHoldObjects {
	public static void main(String[] args) {
		MyHoldObjects myHoldObjects = new MyHoldObjects();
		myHoldObjects.myCollectionDemo();
		println();
		myHoldObjects.myArrayListDemo();
		println();
		myHoldObjects.myLinkedListDemo();
		println();
		myHoldObjects.myStackDemo();
		println();
		myHoldObjects.mySetDemo();
		println();
		myHoldObjects.myLinkedHashMapDemo();
		println();
		myHoldObjects.myHashMapDemo();
		println();
		myHoldObjects.myArrayDemo();
		println();
		myHoldObjects.myIteratorDemo();
	}

	/**
	 * 数组拷贝 数组扩容
	 */
	void myArrayDemo() {
		// 数组拷贝
		int size = 10000;
		int[] src = new int[size];
		int[] des = new int[size];
		System.arraycopy(src, 0, des, 0, size);

		// 数组扩容
		int[] arr = new int[5];
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5;
		println(Arrays.toString(arr));
		println("arr length : " + arr.length);
		// 使用 System.arraycopy()
		int[] newArr = new int[10];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		println(Arrays.toString(newArr));
		println("newArr length : " + newArr.length);
	}

	void myCollectionDemo() {
		Collection<Integer> c = new ArrayList<>(Arrays.asList(1, 2, 3));
		for (Integer i : c) {
			println(i);
		}

		println();
		Integer[] m = {4, 5, 6};
		// 初始 Collection 为某对象
		c.addAll(Arrays.asList(m));

		for (Integer i : c) {
			println(i);
		}
	}

	void myLinkedListDemo() {
		LinkedList<Integer> ll = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		println(ll.peek());
		println(ll.element() + ll.getFirst());
	}

	void myArrayListDemo() {
		// List 初始如果初始对象直接使用 Arrays.asList() 不可以进行 resize
		List<Integer> list = new ArrayList<>(Arrays.asList(7, 8, 10, 10));
		Iterator<Integer> it = list.iterator();
		// 获取List专属的迭代器
		ListIterator<Integer> lit = list.listIterator();

		list.set(2, 9);
		// 截取一段
		list = list.subList(0, 4);
		println(list.toString());

		// 使用 listIterator 实现正序 倒序遍历
		lit = list.listIterator();
		while (lit.hasNext()) {
			println(lit.next());
		}
		while (lit.hasPrevious()) {
			println(lit.previous());
		}
		it = list.iterator();
		// 使用迭代器清空集合
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		/*
		 如果调用remove之前没有调用next是不合法的，会抛出IllegalStateException
		 如果迭代器的指针已经指向了集合的末尾，那么如果再调用next()会返回NoSuchElementException异常
		 */
		println(list.toString());

		/*
		Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。最好使用 ArrayList 而不是 Vector，因为同步操作完全可以由程序员自己来控制；
		Vector 每次扩容请求其大小的 2 倍（也可以通过构造函数设置增长的容量），而 ArrayList 是 1.5 倍。
		 */
		Vector<Integer> vector = new Vector<>(10);

		// 可以使用 Collections.synchronizedList(); 得到一个线程安全的 ArrayList。
		List<Integer> synList = Collections.synchronizedList(list);
		// 也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。
		List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
		/*
		CopyOnWriteArrayList 读写分离
		写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。
		写操作需要加锁，防止并发写入时导致写入数据丢失。
		写操作结束之后需要把原始数组指向新的复制数组。

		CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。
		但是 CopyOnWriteArrayList 有其缺陷：
			内存占用：在写操作时需要复制一个新的数组，使得内存占用为原来的两倍左右；
			数据不一致：读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中。
		所以 CopyOnWriteArrayList 不适合内存敏感以及对实时性要求很高的场景。
		 */
	}

	void myStackDemo() {
		// 不建议使用 ava.util.stack ， 推荐使用 deque——double ended queue（双端队列）实现 LIFO
		// 栈
		Stack<String> s = new Stack<>();
		String hello = "hello it's me";
		String blank = " ";
		for (String s1 : hello.split(blank)) {
			s.push(s1);
		}
		while (!s.empty()) {
			println(s.pop());
		}

		ArrayList<Integer> al = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		// 使用 ArrayList 实现栈
		Stack2<Integer> s2 = new Stack2<>();
		for (Integer i : al) {
			s2.push(i);
		}
		// 相当于 peek()
		println(s2.peek());
		println(s2.toString());
	}

	void mySetDemo() {
		Random rand = new Random(47);
		Set<Integer> s = new HashSet<>();
		// 需要对结果排序就需要使用 TreeSet 替代 HashSet
		SortedSet<Integer> ss = new TreeSet<>(Arrays.asList(100, 200, 300));
		for (int i = 0; i < 10000; i++) {
			s.add(rand.nextInt(10));
		}
		println(ss);
		s.addAll(ss);
		println(s.contains(1));
		println(s);// HashSet的迭代器在输出时“不保证有序”，但也不是“保证无序”
	}

	void myLinkedHashMapDemo() {
		// 取 LinkedHashMap 中的某顺序值
		LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<>();
		lhm.put(1, 2);
		lhm.put(2, 3);
		println(lhm.entrySet().iterator().next());
		println(lhm.entrySet().toArray()[0]);
		/*
		内部维护了一个双向链表，用来维护插入顺序或者 LRU 顺序。
		当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。
		也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

		afterNodeAccess()
		当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。
		也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

		afterNodeInsertion()
		在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。
		evict 只有在构建 Map 的时候才为 false，在这里为 true。

		afterNodeInsertion()
		在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。
		evict 只有在构建 Map 的时候才为 false，在这里为 true。

		removeEldestEntry()
		默认为 false，如果需要让它为 true，需要继承 LinkedHashMap
		并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。
		 */


		// LRUCache
		LRUCache<Integer, String> cache = new LRUCache<>();
		cache.put(1, "a");
		cache.put(2, "b");
		cache.put(3, "c");
		println(cache.get(1));
		cache.put(4, "d");
		println(cache.keySet());
		// [3, 1, 4]
	}

	void myHashMapDemo() {
		/*
		内部包含了一个 Entry 类型的数组 table。Entry 存储着键值对。
		它包含了四个字段，从 next 字段我们可以看出 Entry 是一个链表。即数组中的每个位置被当成一个桶，一个桶存放一个链表。
		HashMap 使用拉链法来解决冲突，同一个链表中存放哈希值和散列桶取模运算结果相同的 Entry。
		 */
		int loop = 10000;
		Random random = new Random(47);
		Map<Integer, Integer> m = new HashMap<>();
		for (int i = 0; i < loop; i++) {
			// Produce a number between 0 and 20
			int r = random.nextInt(10);
			Integer freq = m.get(r);
			m.put(r, freq == null ? 1 : freq + 1);
		}
		println(m);

		/*
		拉链法的工作原理
		新建一个 HashMap，默认大小为 16；
		插入 <K1,V1> 键值对，先计算 K1 的 hashCode 为 115，使用除留余数法得到所在的桶下标 115%16=3。
		插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
		插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> 前面。
		 */
		HashMap<String, String> map = new HashMap<>(16);
		map.put("K1", "V1");
		map.put("K2", "V2");
		map.put("K3", "V3");
		println(map);
		/*
		应该注意到链表的插入是以头插法方式进行的，例如上面的 <K3,V3> 不是插在 <K2,V2> 后面，而是插入在链表头部。
			查找需要分成两步进行：
			计算键值对所在的桶；
		在链表上顺序查找，时间复杂度显然和链表的长度成正比。
		 */

		/*
		HashMap 允许插入键为 null 的键值对。
		但是因为无法调用 null 的 hashCode() 方法，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。
		HashMap 使用第 0 个桶存放键为 null 的键值对。
		使用链表的头插法，也就是新的键值对插在链表的头部，而不是链表的尾部。
		 */

		/*
		设 HashMap 的 table 长度为 M，需要存储的键值对数量为 N，
		如果哈希函数满足均匀性的要求，那么每条链表的长度大约为 N/M，因此查找的复杂度为 O(N/M)。
		为了让查找的成本降低，应该使 N/M 尽可能小，因此需要保证 M 尽可能大，也就是说 table 要尽可能大。
		HashMap 采用动态扩容来根据当前的 N 值来调整 M 值，使得空间效率和时间效率都能得到保证。

		在进行扩容时，需要把键值对重新计算桶下标，从而放到对应的桶上。
		在前面提到，HashMap 使用 hash%capacity 来确定桶下标。
		HashMap capacity 为 2 的 n 次方这一特点能够极大降低重新计算桶下标操作的复杂度。
		假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32：
		对于一个 Key，它的哈希值 hash 在第 5 位：
			为 0，那么 hash%00010000 = hash%00100000，桶位置和原来一致；
			为 1，hash%00010000 = hash%00100000 + 16，桶位置是原位置 + 16。
		 */

		// 从 JDK 1.8 开始，一个桶存储的链表长度大于等于 8 时会将链表转换为红黑树。

		/*
		ConcurrentHashMap 和 HashMap 实现上类似，最主要的差别是 ConcurrentHashMap 采用了分段锁（Segment），
		每个分段锁维护着几个桶（HashEntry），多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（并发度就是 Segment 的个数）。
		Segment 继承自 ReentrantLock。
		默认的并发级别为 16，也就是说默认创建 16 个 Segment。
		 */
		Map<String, String> conCurrentHashMap = new ConcurrentHashMap<>(16);
		conCurrentHashMap.put("A", "a");
		/*
		在执行 size 操作时，需要遍历所有 Segment 然后把 count 累计起来。
		ConcurrentHashMap 在执行 size 操作时先尝试不加锁，如果连续两次不加锁操作得到的结果一致，那么可以认为这个结果是正确的。
		尝试次数使用 RETRIES_BEFORE_LOCK 定义，该值为 2，retries 初始值为 -1，因此尝试次数为 3。
		如果尝试的次数超过 3 次，就需要对每个 Segment 加锁。
		 */
		println(conCurrentHashMap.size());
		/*
		JDK 1.8 使用了 CAS 操作来支持更高的并发度，在 CAS 操作失败时使用内置锁 synchronized。
		并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。
		 */
	}

	void myWeakHashMapDemo() {
		/*
		存储结构
		WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的对象在下一次垃圾回收时会被回收。
		WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。
		 */
		Map<String, String> weakMap = new WeakHashMap<>(16);

		/*
		ConcurrentCache
		Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。
		ConcurrentCache 采取的是分代缓存：
		经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
		不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
		当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
		当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。
		 */
	}

	void myIteratorDemo() {
		for (String s : new IterableClass()) {
			print(s + " ");
		}
	}

	/**
	 * 设定最大缓存空间 MAX_ENTRIES 为 3；
	 * 使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
	 * 覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。
	 *
	 * @param <K>
	 * @param <V>
	 */
	static class LRUCache<K, V> extends LinkedHashMap<K, V> {
		private static final int MAX_ENTRIES = 3;

		@Override
		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > MAX_ENTRIES;
		}

		LRUCache() {
			super(MAX_ENTRIES, 0.75f, true);
		}
	}

	static class IterableClass implements Iterable<String> {
		protected String[] words = ("You are a good girl, but my cock is too big to you.").split(" ");

		@Override
		public Iterator<String> iterator() {
			return new Iterator<String>() {
				private int index = 0;

				@Override
				public boolean hasNext() {
					return index < words.length;
				}

				@Override
				public String next() {
					return words[index++];
				}

				@Override
				public void remove() {
					// Not implemented
					throw new UnsupportedOperationException();
				}
			};
		}
	}
}
