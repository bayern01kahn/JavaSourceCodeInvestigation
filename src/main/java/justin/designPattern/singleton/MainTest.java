package justin.designPattern.singleton;


import justin.designPattern.singleton.hungry.HungrySingleton;
import justin.designPattern.singleton.innerClass.InnerClassSingleton;
import justin.designPattern.singleton.lazy.LazySingleton;

public class MainTest {

	public static void main(String[] args) {
		
		
		//饿汉式： 资源效率不高，可能getInstance()永远不会执行到，但执行该类的其他静态方法或者加载了该类（class.forName)，那么这个实例仍然初始化
		HungrySingleton.getHungrySingleton();
		HungrySingleton.getHungrySingleton().setNumber(8);
		HungrySingleton h1 = HungrySingleton.getHungrySingleton();
		HungrySingleton h2 = HungrySingleton.getHungrySingleton();
		System.out.println("前后2次获取的是否是同一个饿汉式单例实例："+ (h1 == h2));
		
		//懒汉式： 懒汉式在单个线程中没有问题，但多个线程同事访问的时候就可能同时创建多个实例，而且这多个实例不是同一个对象，虽然后面创建的实例会覆盖先创建的实例，但是还是会存在拿到不同对象的情况。解决这个问题的办法就是加锁synchonized，第一次加载时不够快，多线程使用不必要的同步开销大
		LazySingleton l1 = LazySingleton.getLazySingleton();
		LazySingleton l2 = LazySingleton.getLazySingleton();
		System.out.println("前后2次获取的是否是同一个懒汉式单例实例："+ (l1 == l2));
		
		
		
		//加锁式懒汉：资源利用率高，不执行getInstance()就不被实例，可以执行该类其他静态方法
		LazySingleton ls1 = LazySingleton.getSynchronizedLazySingleton();
		LazySingleton ls2 = LazySingleton.getSynchronizedLazySingleton();
		System.out.println("前后2次获取的是否是同一个懒汉式线程安全单例实例："+ (ls1 == ls2));
		

		
		//静态内部类的单例 ：资源利用率高，不执行getInstance()不被实例，可以执行该类其他静态方法
		InnerClassSingleton i1 = InnerClassSingleton.getInstance();
		InnerClassSingleton i2 = InnerClassSingleton.getInstance();
		System.out.println("前后2次获取的是否是同一个静态内部单例实例："+ (i1 == i2));
		
		
	}

}
