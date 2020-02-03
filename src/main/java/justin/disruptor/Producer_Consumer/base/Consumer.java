package justin.disruptor.Producer_Consumer.base;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        long id =Thread.currentThread().getId();
        System.out.println("Consumer: " + id + " start!!");
        Random r = new Random();

        try {
            while (isRunning) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getData() * data.getData();
                    System.out.println("Consumer: "+id+ " 消费: get "+data +" from queue. 计算: "+ MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
            System.out.println("Consumer: " + id +" Exit !! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}