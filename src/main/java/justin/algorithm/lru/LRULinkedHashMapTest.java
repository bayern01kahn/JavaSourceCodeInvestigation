package justin.algorithm.lru;

import org.junit.Test;

public class LRULinkedHashMapTest {

	@Test
	public void test() {

		LRULinkedHashMap<Integer, Integer> cache = new LRULinkedHashMap<Integer, Integer>(3);

		for (int i = 0; i < 10; i++) {
			cache.put(i, i * i);
		}

		System.out.println("插入10个键值对后，缓存内容：");

		System.out.println(cache + "\n");

		System.out.println("访问键值为7的节点后，缓存内容：");

		cache.get(7);

		System.out.println(cache + "\n");

		System.out.println("插入键值为1的键值对后，缓存内容：");

		cache.put(1, 1);

		System.out.println(cache);

	}

}