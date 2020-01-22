package justin.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class CountDownLatchDemo1 extends Thread {

    private static final int V = 5;

    /***
     * 用于debug和深入理解 countdownlatch和AQS的源码和实现原理
     *
     * 放置countdownlatch.await() 的地方 都会把调用者放入 同步等待队列,
     * 当 count=0 时, 谁会被先唤醒 是随机的
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(V);
        StringBuffer sb = new StringBuffer(255);
        Thread waitT1 = new Thread(() -> {
            try {
                Thread.currentThread().setName("WaitThread-1");

                sb.append("waitThread1 等待任务就绪.\n");
                //countDownLatch.await(5, TimeUnit.SECONDS);
                countDownLatch.await();
                sb.append("waitThread1 等待结束.\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread waitT2 = new Thread(() -> {
            try {
                Thread.currentThread().setName("WaitThread-2");
                sb.append("waitThread2 等待任务就绪.\n");
                //countDownLatch.await(1, TimeUnit.SECONDS);
                countDownLatch.await();
                //countDownLatch.await();
                sb.append("waitThread2 等待结束.\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        waitT1.start();
        waitT2.start();

        Thread.sleep(3000);

        for(int i=0; i<V; i++){
            final int tmp = i;

            Thread task = new Thread(() -> {
                Thread.currentThread().setName("Task-Thread-"+tmp);
                sb.append(String.format("task[%s] 准备就绪:\n", tmp));
                countDownLatch.countDown();
                sb.append(String.format("task[%s] 结束:\n", tmp));
            });
            task.start();
        }


        waitT1.join();
        waitT2.join();

        sb.append("Test Done\n");
        System.out.println(sb.toString());
    }
}
