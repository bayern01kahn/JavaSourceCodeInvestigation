package justin.algorithm.Leetcode.Q1115;

import javafx.concurrent.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore_Solution {
    private int n;

    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    public Semaphore_Solution(int n) {
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

            foo.acquire();
            System.out.print("foo");
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            System.out.print("bar");
            foo.release();
        }
    }
}

