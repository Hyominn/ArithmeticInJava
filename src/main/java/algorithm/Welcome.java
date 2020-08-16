package algorithm;


/**
 * This is the first sample program
 *
 * @author niezhiyong
 * @version 2020-2-26 17:42:20
 * @since 0.0.1
 */
public class Welcome {
	public static final double PI = 3.14159265358979323846;

	/**
	 * main 方法不对任何对象进行操作。事实上，在启动程序时还没有任何一个对象。静态的main 方法将执行并创建程序所需要的对象
	 */
	public static void main(String[] args) {
		/* 缓存容量 */
		LRUCache cache = new LRUCache(2);

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		// 返回  1
		cache.put(3, 3);
		// 该操作会使得密钥 2 作废
		System.out.println(cache.get(2));
		// 返回 -1 (未找到)
		cache.put(4, 4);
		// 该操作会使得密钥 1 作废
		System.out.println(cache.get(1));
		// 返回 -1 (未找到)
		System.out.println(cache.get(3));
		// 返回  3
		System.out.println(cache.get(4));
		// 返回  4

		System.out.println(cache.map.toString());
	}
}



