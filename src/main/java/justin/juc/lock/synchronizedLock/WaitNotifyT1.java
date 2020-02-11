package justin.juc.lock.synchronizedLock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class WaitNotifyT1 {

    public class Lock {

        @Getter
        private final Object lock = new Object();
    }

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


    /**
     *
     *   read me first !!!!!!!!!!!!!!!
     *
     *
     *   核心在: step 1
     *
     *
     *   这个例子旨在说明 wait() notify 在内部锁内的作用和执行流程
     *
     * 1.在线程进入synchronized方法或者代码块，相当于获取监视器锁成功，如果此时成功调用wait()系列方法，
     *   那么它会立即释放监视器锁，并且添加到等待集合(Wait Set)中进行阻塞等待。
     * 2.由于已经有线程释放了监视器锁，那么在另一个线程进入synchronized方法或者代码块之后，
     *   它可以调用notify(All)方法唤醒等待集合中正在阻塞的线程，但是这个唤醒操作并不是调用notify(All)方法后立即生效，
     *   而是在该线程退出synchronized方法或者代码块之后才生效。
     * 3.从wait()方法阻塞过程中被唤醒的线程会竞争监视器目标对象的控制权，一旦重新控制了对象，那么线程的同步状态就会恢复
     *   到步入synchronized方法或者代码块时候的状态(也就是成功获取到对象监视器锁时候的状态)，这个时候线程才能够继续执行。
     */
    public static void main(String[] args) throws Exception {

        WaitNotifyT1 waitMain = new WaitNotifyT1();
        waitMain.test();
    }

    public void test() throws Exception {
        final Lock lock = new Lock();
        new Thread(new  WaitRunnable(lock), "WaitThread-1").start();
        new Thread(new  WaitRunnable(lock), "WaitThread-2").start();
        new Thread(new  WaitRunnable(lock), "WaitThread-3").start();
        new Thread(new  WaitRunnable(lock), "WaitThread-4").start();
        new Thread(new  WaitRunnable(lock), "WaitThread-5").start();
        Thread.sleep(50);
        new Thread(new  NotifyRunnable(lock), "NotifyThread").start();
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @RequiredArgsConstructor
    private static class WaitRunnable implements Runnable {

        private final Lock lock;

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(String.format("[%s]-线程[%s]获取锁成功,准备执行wait方法", F.format(LocalDateTime.now()),
                        Thread.currentThread().getName()));
                while (true) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        //ignore
                    }
                    System.out.println(String.format("[%s]-线程[%s]从wait中唤醒,准备exit", F.format(LocalDateTime.now()),
                            Thread.currentThread().getName()));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        //ignore
                    }
                    break;
                }
            }
        }
    }

    @RequiredArgsConstructor
    private static class NotifyRunnable implements Runnable {

        private final Lock lock;

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(String.format("[%s]-线程[%s]获取锁成功,准备执行notifyAll方法", F.format(LocalDateTime.now()),
                        Thread.currentThread().getName()));
                lock.notifyAll();
                System.out.println(String.format("[%s]-线程[%s]先休眠3000ms", F.format(LocalDateTime.now()),
                        Thread.currentThread().getName()));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //ignore
                }
                System.out.println(String.format("[%s]-线程[%s]准备exit", F.format(LocalDateTime.now()),
                        Thread.currentThread().getName()));

                System.out.println(String.format("[%s]-线程[%s]退出Synchronized方法", F.format(LocalDateTime.now()),
                        Thread.currentThread().getName()));
            }
        }
    }



}