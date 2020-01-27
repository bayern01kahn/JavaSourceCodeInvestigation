package justin.juc.lock.reentrantLock.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T1 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final T1 task = new T1();

//        ReadReadTest(task);
//        WriteWriteTest(task);
        ReadWriteTest(task);
//        WriteReadTest(task);

    }




    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }



    public static void ReadReadTest(T1 task){
            Thread t1 = new Thread(() -> task.read());
            t1.setName("t1-read");

            Thread t2 = new Thread(() -> task.read());
            t2.setName("t2-read");

            t1.start();
            t2.start();
    }

    public static void WriteWriteTest(T1 task){
        Thread t1 = new Thread(() -> task.write());
        t1.setName("t1-write");

        Thread t2 = new Thread(() -> task.write());
        t2.setName("t2-write");

        t1.start();
        t2.start();
    }

    public static void ReadWriteTest(T1 task){
        Thread t1 = new Thread(() -> task.read());
        t1.setName("t1-read");

        Thread t2 = new Thread(() -> task.write());
        t2.setName("t2-write");

        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }


    public static void WriteReadTest(T1 task){
        Thread t1 = new Thread(() -> task.write());
        t1.setName("t1-write");

        Thread t2 = new Thread(() -> task.read());
        t2.setName("t2-read");

        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
