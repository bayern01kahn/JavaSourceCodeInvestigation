package justin.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java 四种引用和GC关系
 * @author justin
 *
 */
public class ReferenceGCTest {

	public static void main(String[] args) {

		StrongReferenceTest();
		SoftReferenceTest();
		WeakReferenceTest();
		PhantomReferenceTest();
	}

	public static void StrongReferenceTest(){
		System.out.println("强引用测试：");
		Object obj = new Object();
		System.out.println(obj);
		System.gc();
		System.out.println(obj);//强制回收后 强引用任然存在
		System.out.println();
	}

	public static void SoftReferenceTest(){
		System.out.println("软引用测试：");
		Object reference = new Object();
		System.out.println(reference);
		Reference root = new SoftReference(reference);
		reference = null; // Object对象只有软引用
		System.gc();
		System.out.println(root.get()); //强制回收后 软引用任然存在，不一定会被回收
		System.out.println();
	}
	
	public static void WeakReferenceTest(){
		System.out.println("弱引用测试：");
		Object reference = new Object();
		System.out.println(reference);
		Reference root = new WeakReference(reference);
		reference = null; // Object对象只有软引用
		System.gc();
		System.out.println(root.get());//强制GC后， 弱引用会被立即回收
		System.out.println();
	}
	
	public static void PhantomReferenceTest(){
		System.out.println("虚引用测试：");
		
		final ReferenceQueue queue = new ReferenceQueue();
		new Thread(new Runnable() {
		  @Override
		  public void run() {
		      while (true) {
		          try {
		              Reference reference = queue.remove();
		              System.out.println(reference + "回收了");
		          } catch (InterruptedException e) {

		          }
		      }
		  }
		}).start();

		Object reference = new Object();
		System.out.println(reference);
		Reference root = new PhantomReference<Object>(reference, queue);
		reference = null; // Object对象只有软引用
		System.gc();
		System.out.println(root.get());
	}
	
}
