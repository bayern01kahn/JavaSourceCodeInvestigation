package justin.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {

	private Lock lock = new ReentrantLock();
	private int number = 0;
	
	public int getNumber(){
		return this.number;
	}
	
	public void increase(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.number++;
	}
	
	public void synIncrease(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			this.number++;
		} 
	}
	
	public void lockIncrease(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.lock();
		try {
			this.number++;
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final VolatileDemo volDemo = new VolatileDemo();
		for(int i = 0 ; i < 500 ; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//volDemo.increase();   //1种.失去原子性操作
					//volDemo.synIncrease();  //2种.加入 synchronized
					volDemo.lockIncrease();   //3种.使用  concurrent.locks.ReentrantLock
				}
			}).start();
		}
		
		//如果还有子线程在运行，主线程就让出CPU资源，直到所有的子线程都运行完了，主线程再继续往下执行
		while(Thread.activeCount() > 1){
			Thread.yield();
		}
		System.out.println("number : " + volDemo.getNumber());
	}

}
