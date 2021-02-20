package justin.algorithm.Leetcode.Q1115;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_Condition_Solution {
    private int n;

    private volatile int tag = 1;

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public ReentrantLock_Condition_Solution(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        Semaphore_Solution fooBar = new Semaphore_Solution(10);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable r1 = () -> {
            try {
                fooBar.foo(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                fooBar.bar(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.submit(r1);
        executorService.submit(r2);

        executorService.shutdown();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            if (tag != 1) {
                c1.await();
            }
            System.out.print("foo");
            tag = 2;
            c2.signal();
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            if (tag != 2) {
                c2.await();
            }
            System.out.print("bar");
            tag = 1;
            c1.signal();
            lock.unlock();
        }
    }
}
