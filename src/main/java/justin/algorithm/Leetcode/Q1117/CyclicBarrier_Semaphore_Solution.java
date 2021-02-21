package justin.algorithm.Leetcode.Q1117;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CyclicBarrier_Semaphore_Solution {

    /**
     * 执行方式为 当设置线程数都执行完成时,之后第二个参数的runnable的 run 方法
     * 多用于并行计算然后汇总最终的值
     */
    private CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println(" => H: "+semaphoreH.availablePermits()+ " - O: "+semaphoreO.availablePermits());
            semaphoreH.release(2);
            semaphoreO.release();
            //System.out.print("  |Barrier|  ");
        }
    });

    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    public CyclicBarrier_Semaphore_Solution() {
        semaphoreH = new Semaphore(2);
        semaphoreO = new Semaphore(1);
    }

    public static void main(String[] args) {
        CyclicBarrier_Semaphore_Solution h2o = new CyclicBarrier_Semaphore_Solution();
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            try {
                h2o.hydrogen(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                h2o.oxygen(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r2);

        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r2);

        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r1);

        executorService.submit(r2);
        executorService.submit(r2);
        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r1);
        executorService.submit(r1);


        executorService.shutdown();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        System.out.print("H");
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        System.out.print("O");
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
