package justin.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo extends Thread {

    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long start = System.currentTimeMillis();

        new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1");
            countDownLatch.countDown(); //3-1=2
        }).start();
        new Thread(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2");
            countDownLatch.countDown();//2-1=1
        }).start();
        new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread3");
            countDownLatch.countDown();//1-1=0
        }).start();
        countDownLatch.await();

        System.out.println("Time cost: " + (System.currentTimeMillis() - start));
    }
}
