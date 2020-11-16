package justin.juc.Unsafe.LockSupport;

import java.util.concurrent.locks.LockSupport;


/**
 * park和unpark可以实现类似wait和notify的功能，但是并不和wait和notify交叉，也就是说unpark不会对wait起作用，notify也不会对park起作用。
 * park和unpark的使用不会出现死锁的情况
 * blocker的作用是在dump线程的时候看到阻塞对象的信息
 *
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
