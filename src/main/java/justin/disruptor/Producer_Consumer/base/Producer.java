package justin.disruptor.Producer_Consumer.base;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        long id = Thread.currentThread().getId();
        System.out.println("Producer: " + id+ " start!!");
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println("Producer: "+id+" 生产: put " + data + " into queue");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put data: " + data);
                }
            }
            System.out.println("Producer: " + id+ " Exit!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
