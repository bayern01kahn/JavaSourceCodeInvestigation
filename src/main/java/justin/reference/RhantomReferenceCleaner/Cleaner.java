package justin.reference.RhantomReferenceCleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Cleaner extends PhantomReference<Object> {

	// Dummy reference concurrent.queue, needed because the PhantomReference constructor
	// insists that we pass a concurrent.queue. Nothing will ever be placed on this concurrent.queue
	// since the reference handler invokes cleaners explicitly.
	// 就像英文注释所说的，这货没啥卵用。后面我会讲到的。
	private static final ReferenceQueue<Object> dummyQueue = new ReferenceQueue<Object>();

	// Doubly-linked list of live cleaners, which prevents the cleaners
	// themselves from being GC'd before their referents
	// 所有的cleaner都会被加到一个双向链表中去，这样做是为了保证在referent被回收之前
	// 这些Cleaner都是存活的。
	static private Cleaner first = null;

	private Cleaner next = null, prev = null;

	// 构造的时候把自己加到双向链表中去
	private static synchronized Cleaner add(Cleaner cl) {
		if (first != null) {
			cl.next = first;
			first.prev = cl;
		}
		first = cl;
		return cl;
	}

	// clean方法会调用remove把当前的cleaner从链表中删除。
	private static synchronized boolean remove(Cleaner cl) {
		// If already removed, do nothing
		if (cl.next == cl)
			return false;

		// Update list
		if (first == cl) {
			if (cl.next != null)
				first = cl.next;
			else
				first = cl.prev;
		}
		if (cl.next != null)
			cl.next.prev = cl.prev;
		if (cl.prev != null)
			cl.prev.next = cl.next;

		// Indicate removal by pointing the reference.RhantomReferenceCleaner to itself
		cl.next = cl;
		cl.prev = cl;
		return true;
	}

	// 用户自定义的一个Runnable对象，
	private final Runnable thunk;

	// 私有有构造函数，保证了用户无法单独地使用new来创建Cleaner。
	private Cleaner(Object referent, Runnable thunk) {
		super(referent, dummyQueue);
		this.thunk = thunk;
	}

	/**
	 * 所有的Cleaner都必须通过create方法进行创建。
	 */
	public static Cleaner create(Object ob, Runnable thunk) {
		if (thunk == null)
			return null;
		return add(new Cleaner(ob, thunk));
	}

	/**
	 * 这个方法会被Reference Handler线程调用，来清理资源。
	 */
	public void clean() {
		if (!remove(this))
			return;
		try {
			thunk.run();
		} catch (final Throwable x) {
			AccessController.doPrivileged(new PrivilegedAction<Void>() {
				public Void run() {
					if (System.err != null)
						new Error("Cleaner terminated abnormally", x).printStackTrace();
					System.exit(1);
					return null;
				}
			});
		}
	}
}
