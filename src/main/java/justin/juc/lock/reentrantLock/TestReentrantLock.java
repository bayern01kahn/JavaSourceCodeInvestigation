package justin.juc.lock.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock implements Runnable {
    public static ReentrantLock rtlk1 = new ReentrantLock();//默认的是false，不公平锁
    private ReentrantLock rtlk2 = new ReentrantLock(true);//公平锁，与不公平锁来说，要慢很多。
    @Override
    public void run() {
//        tryLockTest(); //可能就只打印一次。
//        lockTest();//一定会打印两次。
//        tryLockTest();//超时就返回，可能只打印一次。
//        lockInterruptTest();//中断使用时间过长的操作。

//        trylockTimeTest();

    }
    public static void main(String[] args) {
        TestReentrantLock t1 = new TestReentrantLock();
        Thread tr1 = new Thread(t1);
        Thread tr2 = new Thread(t1);
        tr1.start();
        tr2.start();
    }

    /*
    1.0 （1）使用场景：①比如一个  定时任务, 第一次定时任务未完成,  重复发起了第二次,直接返回flase;
                       ②用在 界面交互时，点击执行较长时间请求操作时，防止多次点击，导致后台重复执行。
    * */
    public static void tryLockTest() {
        if (rtlk1.tryLock()) {//可能会立即返回--->只打印一次
            //如果已经被lock，则立刻返回false 就不会等待了，
            //达到忽略操作的效果，当执行1000线程时，有些未获得对象的线程，会自动跳过
            try {
                System.out.println("lk lk111 " + Thread.currentThread().getName());
            } finally {
                rtlk1.unlock();
            }
        }
    }

    /*
    2.0  (2)同步操作 类似于synchronized  如果被其它资源锁定，会在此等待锁释放，达到暂停的效果
            ReentrantLock存在公平锁与非公平锁  而且synchronized都是公平的
    * */
    public static void lockTest() {
        try {//会一直等待， 永远打印两次
            rtlk1.lock();//如果被其它资源锁定，会在此 等待 锁释放，达到暂停的效果
            System.out.println("lk lk111 " + Thread.currentThread().getName());
        } finally {
            rtlk1.unlock();
        }
    }

    /*

    3. （3） 使用场景:(1)如果发现该操作正在执行,等待一段时间，
             如果规定时间未得到锁,放弃。防止资源处理不当，线程队列溢出,出现死锁
     */
    public static void trylockTimeTest() {
        try {
            if (rtlk1.tryLock(5, TimeUnit.SECONDS)) {  //如果已经被lock，尝试等待5s，看是否可以获得锁，如果5s后仍然无法获得锁则返回false继续执行
                try {
//操作
                    System.out.println("aaaa" + Thread.currentThread().getName());
                } finally {
                    rtlk1.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); //当前线程被中断时(interrupt)，会抛InterruptedException
        }
    }

    /*
     4. (4.0) 使用场景：(1)中断正在进行的操作,立刻释放锁,继续下一操作.
             比如 取消正在同步运行的操作，来防止不正常操作长时间占用造成的阻塞
    **/
    public static void lockInterruptTest(){
        try {
            rtlk1.lockInterruptibly();
//操作
            System.out.println("aaaa"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rtlk1.unlock();
        }
    }

}
