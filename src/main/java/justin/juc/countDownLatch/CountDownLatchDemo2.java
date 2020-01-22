package justin.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo2 extends Thread {


    /**
     * 同一个线程,可以多次执行 countdown(). 也有效.  当count=0,await()的调用者会解除阻塞.
     * 之后再执行 countdown() 不会导致 count为负数. 也不会在影响之前被阻塞的线程
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        long start = System.currentTimeMillis();



        for(int i=0; i<20; i++){
            final int tmp = i;

//            if(countDownLatch.getCount() == 0 ){
//                break;
//            }
            Thread task = new Thread(() -> {
                Thread.currentThread().setName("Thread"+tmp);
                System.out.println(String.format("task[%s] 准备就绪:\n", tmp));
                countDownLatch.countDown();
                countDownLatch.countDown();
                countDownLatch.countDown();

                System.out.println(Thread.currentThread().getName()+" => 剩余Count:"+ countDownLatch.getCount());
                System.out.println(String.format("task[%s] 结束:\n", tmp));
            });
            task.start();

        }
        countDownLatch.await();
        System.out.println("==> Main执行已结束!. Time cost: " + (System.currentTimeMillis() - start));
    }
}
