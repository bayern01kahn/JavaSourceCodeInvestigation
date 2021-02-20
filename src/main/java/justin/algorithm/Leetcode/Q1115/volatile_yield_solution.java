package justin.algorithm.Leetcode.Q1115;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class volatile_yield_solution {
    private int n;

    private volatile boolean tag = false;

    public volatile_yield_solution(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws InterruptedException {
        volatile_yield_solution fooBar = new volatile_yield_solution(10);

        Thread t1 = new Thread();
        Thread t2 = new Thread();
        fooBar.foo(t1);
        fooBar.bar(t2);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            if(tag){
                Thread.yield();
            }
            System.out.print("foo");
            tag = true;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if(!tag){
                Thread.yield();
            }
            System.out.print("bar");
            tag = false;
        }
    }
}
