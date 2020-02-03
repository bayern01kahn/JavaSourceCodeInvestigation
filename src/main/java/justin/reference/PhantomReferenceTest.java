package justin.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;


/**
 * 利用引用队列，我们可以在对象处于相应状态时（对于幻象引用，就是前面说的被 finalize 了，处于幻象可达状态），执行后期处理逻辑。
 * @author justin
 *
 */
public class PhantomReferenceTest {

	public static void main(String[] args) {
		Object counter = new Object();
		ReferenceQueue refQueue = new ReferenceQueue<>();
		PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
		counter = null;
		System.gc();
		try {
		    // Remove 是一个阻塞方法，可以指定 timeout，或者选择一直阻塞
		    Reference<Object> ref = refQueue.remove(1000L);
		    if (ref != null) {
		        // do something
		    	System.out.println("利用引用队列，我们可以在对象处于相应状态时（对于幻象引用，就是前面说的被 finalize 了，处于幻象可达状态），执行后期处理逻辑");
		    }
		} catch (InterruptedException e) {
		    // Handle it
		}
	}
}
