package justin.algorithm.Leetcode.Q1114;

import java.util.concurrent.locks.LockSupport;

class Foo {

    public volatile boolean status1st = false;
    public volatile boolean status2rd = false;

    public Foo() {

    }

    public static void main(String[] args) throws InterruptedException {

        Foo foo = new Foo();

        Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();

        foo.second(t2);
        foo.first(t1);
        foo.third(t3);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        status1st = true;
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!status1st){

        }


        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        status2rd = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(!status2rd){

        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
