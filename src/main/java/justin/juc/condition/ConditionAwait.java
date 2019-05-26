package justin.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class ConditionAwait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionAwait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        try {
            lock.lock(); //竞争锁
            try {
                System.out.println("begin - ConditionAwait");
                condition.await();//阻塞(1. 释放锁, 2.阻塞当前线程, FIFO（单向、双向）)
                System.out.println("end - ConditionAwait");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lock.unlock();//释放锁
        }


    }


}
