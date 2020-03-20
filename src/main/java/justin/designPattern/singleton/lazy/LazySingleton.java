package justin.designPattern.singleton.lazy;

public class LazySingleton {
	//私有化构造函数
	private LazySingleton(){}
	
	private static LazySingleton ls = null; //先不初始化
	
	public static LazySingleton getLazySingleton(){
		if(ls == null){
			ls = new LazySingleton();  //多个线程判断instance都为null时，在执行new操作时多线程会出现重复情况
		}
		return ls;
	}
	
	//懒汉式在单个线程中没有问题，但多个线程同事访问的时候就可能同时创建多个实例，而且这多个实例不是同一个对象
	//虽然后面创建的实例会覆盖先创建的实例，但是还是会存在拿到不同对象的情况。解决这个问题的办法就是加锁synchonized，第一次加载时不够快，多线程使用不必要的同步开销大
	
	//加锁式懒汉单例
	public static LazySingleton getSynchronizedLazySingleton() {
        if (ls == null) {
                synchronized (LazySingleton.class) {
                        if (ls == null) {
                                ls = new LazySingleton();
                        }
                }
        }
        return ls;
	}
}
