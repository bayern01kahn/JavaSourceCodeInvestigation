package justin.juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {


        reentrantTest();
        //Thread.currentThread().setDaemon(true);
//        fairLockTest();
//        unfairLockTest();

    }



    public static void reentrantTest(){
        ReentrantLock lock = new ReentrantLock();

        for (int i = 1; i <= 10; i++) {
            lock.lock();
            System.out.println("第"+i+"次加锁");
        }

        for(int i=1;i<=3;i++){
            try {

            } finally {
                lock.unlock();
                System.out.println("第"+i+"次释放锁");
            }
        }
    }

    public static void fairLockTest(){
        Lock fairLock = new ReentrantLock(true);

        for(int i=0;i<5;i++){
            new Thread(new ThreadDemo(fairLock, i)).start();
        }
    }

    public static void unfairLockTest(){
        Lock lock = new ReentrantLock();

        for(int i=0;i<5;i++){
            new Thread(new ThreadDemo(lock, i)).start();
        }
    }



    static class ThreadDemo implements Runnable {
        Integer id;
        Lock lock;

        public ThreadDemo(Lock lock,Integer id) {
            this.lock = lock;
            this.id = id;
        }

        @Override

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<2;i++){
                lock.lock();
                System.out.println("获得锁的线程："+id);
                lock.unlock();
            }
        }
    }




}
