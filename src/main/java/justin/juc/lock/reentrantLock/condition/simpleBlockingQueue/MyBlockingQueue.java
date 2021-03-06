package justin.juc.lock.reentrantLock.condition.simpleBlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
    int size;//阻塞队列最大容量

    //ReentrantLock lock = new ReentrantLock();
    ReentrantLock lock = new ReentrantLock(false);

    LinkedList<E> list=new LinkedList<>();//队列底层实现

    Condition notFull = lock.newCondition();//队列满时的等待条件
    Condition notEmpty = lock.newCondition();//队列空时的等待条件

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void enqueue(E e, String threadName) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() ==size)//队列已满,在notFull条件上等待
            {
                notFull.await();
            }

            list.add(e);//入队:加入链表末尾
            System.out.println(e + " =>入队("+threadName+")");
            notEmpty.signal(); //通知在notEmpty条件上等待的线程
        } finally {
            lock.unlock();
        }
    }

    public E dequeue(String threadName) throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0)//队列为空,在notEmpty条件上等待
            {
                notEmpty.await();
            }

            e = list.removeFirst();//出队:移除链表首元素
            System.out.println(e + " =>出队("+threadName+")");
            notFull.signal();//通知在notFull条件上等待的线程
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final int SIZE = 3;

        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(SIZE);
        for (int i = 0; i < 20; i++) {
            int data = i;
            new Thread(() -> {
                try {
                    Thread.currentThread().setName(Thread.currentThread().getName());
                    queue.enqueue(data, Thread.currentThread().getName());
                } catch (InterruptedException e) {

                }
            }).start();

        }
        for(int i=0;i<20;i++){
            new Thread(() -> {
                try {
                    Thread.currentThread().setName(Thread.currentThread().getName());
                    Integer data = queue.dequeue(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
