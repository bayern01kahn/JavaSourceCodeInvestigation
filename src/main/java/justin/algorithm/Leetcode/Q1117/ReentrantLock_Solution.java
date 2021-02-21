package justin.algorithm.Leetcode.Q1117;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLock_Solution {


    volatile int hNum = 0;

    ReentrantLock lock = new ReentrantLock();
    // O元素 满足条件的队列
    Condition oFull = lock.newCondition();
    // H元素 满足条件的队列
    Condition hFull = lock.newCondition();
    /**
     *  hFull.await();     表示当前H 不够,需要O那边等待
     *  hFull.signal();    表示当前H 够了, 释放O那边开始执行
     *  oFull.await();     表示当前H 够了, h那边需要等待.
     *  oFull.signalAll(); 表示当前O 够了, h那边可以开始执行
    */


    public ReentrantLock_Solution() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hNum == 2) {
                hFull.signal();  //表示当前H 够了, 释放O那边开始执行
                oFull.await();   //表示当前H 够了, h那边需要等待.
            }
            System.out.print("H");
            hNum++;
            // 避免最后都是H元素，O元素的线程一直在wait
            if (hNum == 2) {
                hFull.signal();  //表示当前H 够了, 释放O那边开始执行
            }
        } finally {
            lock.unlock();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (hNum != 2) hFull.await();  //表示当前H 不够,需要O那边等待
            System.out.print("O");
            hNum = 0;
            oFull.signalAll();                //表示当前O 够了, h那边可以开始执行
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLock_Solution h2o = new ReentrantLock_Solution();
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            try {
                h2o.hydrogen(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                h2o.oxygen(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r2);

        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r2);

        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r1);

        executorService.submit(r2);
        executorService.submit(r2);
        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r1);


        executorService.shutdown();
    }

}
