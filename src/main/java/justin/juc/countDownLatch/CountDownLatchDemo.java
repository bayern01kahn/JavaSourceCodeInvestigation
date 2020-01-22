package justin.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo extends Thread {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long start = System.currentTimeMillis();

        new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().setName("ThreadA");
            System.out.println("ThreadA");
            countDownLatch.countDown(); //3-1=2
        }).start();
        new Thread(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().setName("ThreadB");
            System.out.println("ThreadB");
            countDownLatch.countDown();//2-1=1
        }).start();
        new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().setName("ThreadC");
            System.out.println("ThreadC");
            countDownLatch.countDown();//1-1=0
        }).start();
        countDownLatch.await();

        System.out.println("Time cost: " + (System.currentTimeMillis() - start));
    }
}
