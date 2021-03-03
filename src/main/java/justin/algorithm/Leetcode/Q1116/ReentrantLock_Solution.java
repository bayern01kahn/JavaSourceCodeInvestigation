package justin.algorithm.Leetcode.Q1116;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ReentrantLock_Solution {
    private int n;

    private static AtomicInteger point = new AtomicInteger(0);

    public static ReentrantLock lock = new ReentrantLock();
    static Condition cZero = lock.newCondition();
    static Condition cOdd = lock.newCondition();
    static Condition cEven = lock.newCondition();

    public ReentrantLock_Solution(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (point.get() < n) {
            printNumber.accept(0);
            point.incrementAndGet();
            cOdd.signal();
            cEven.signal();
            cZero.await();
        }
        lock.unlock();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (point.get() <= n && point.get()>0) {
            if (!isOdd(point.get())) {
                printNumber.accept(point.intValue());
                cZero.signal();
            }
            if(point.get() == n) {
                break;
            }
            cEven.await();
        }
        lock.unlock();
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (point.get() <= n) {
            if (isOdd(point.get())) {
                printNumber.accept(point.intValue());
                cZero.signal();
            }
            if(point.get() == n) {
                break;
            }
            cOdd.await();
        }
        lock.unlock();
    }

    public boolean isOdd(int i) {
        return (i & 1) == 1;   //利用与运算
    }


    /**
     *
     *
     * 本地run没有问题 但是 不确定leetcode 为什么过不了...
     *
     *
     */
    public static void main(String[] args) {
        ReentrantLock_Solution zeroEvenOdd = new ReentrantLock_Solution(5);

        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}