package justin.juc.lock.synchronizedLock.demo.simpleBlockingQueue;

import java.util.stream.IntStream;

public class DefaultBlockingQueue<T> implements BlockingQueue<T> {

    private Object[] elements;
    private final Object notEmpty = new Object();
    private final Object notFull = new Object();
    private int count;
    private int takeIndex;
    private int putIndex;

    public DefaultBlockingQueue(int capacity) {
        this.elements = new Object[capacity];
    }

    @Override
    public void put(T value) throws InterruptedException {
        synchronized (notFull) {
            while (count == elements.length) {
                notFull.wait();
            }
        }
        final Object[] items = this.elements;
        items[putIndex] = value;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
        synchronized (notEmpty) {
            notEmpty.notify();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T take() throws InterruptedException {
        synchronized (notEmpty) {
            while (count == 0) {
                notEmpty.wait();
            }
        }
        final Object[] items = this.elements;
        T value = (T) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        synchronized (notFull) {
            notFull.notify();
        }
        return value;
    }


    /**
     * 简单的单生产者-多消费者的模型
     */
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new DefaultBlockingQueue<>(20);
        Runnable r = () -> {
            while (true) {
                try {
                    String take = queue.take();
                    System.out.println(String.format("线程%s消费消息-%s", Thread.currentThread().getName(), take));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r, "thread-1").start();
        new Thread(r, "thread-2").start();
        new Thread(r, "thread-3").start();
        new Thread(r, "thread-4").start();

        IntStream.range(0, 20).forEach(i -> {
            try {
                queue.put(String.valueOf(i));
            } catch (InterruptedException e) {
                //ignore
            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }
}