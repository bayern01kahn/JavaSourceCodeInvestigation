package justin.algorithm.Leetcode.Q1195;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class Solution {

    ReentrantLock lock = new ReentrantLock();
    Condition number = lock.newCondition();
    Condition fizz = lock.newCondition();
    Condition buzz = lock.newCondition();
    Condition fizzbuzz = lock.newCondition();

    private int n;
    private volatile int i = 0;

    public Solution(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        while (i<n){
            fizz.await();
            if(i!=0 && i%3==0 && i%5!=0){
                //printBuzz.run();
                System.out.print("fizz,");
                number.signal();
            }
        }
        lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        while (i<n){
            buzz.await();
            if (i!=0 &&i % 5 == 0 && i % 3 != 0) {
                //printBuzz.run();
                System.out.print("buzz,");
                number.signal();
            }
        }
        lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        while (i<n){
            fizzbuzz.await();
            if (i!=0 && i % 3 == 0 && i % 5 == 0) {
                //printFizzBuzz.run();
                System.out.print("fizzbuzz,");
                number.signal();
            }
        }
        lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while(i<n){
            i++;
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                System.out.print(",");
            } else {
                fizz.signal();
                buzz.signal();
                fizzbuzz.signal();
                number.await();
            }
        }

        fizz.signalAll();
        buzz.signalAll();
        fizzbuzz.signalAll();

        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Solution solution = new Solution(1);

        new Thread(() -> {
            try {
                solution.fizz(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                solution.buzz(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                solution.fizzbuzz(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                solution.number(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}