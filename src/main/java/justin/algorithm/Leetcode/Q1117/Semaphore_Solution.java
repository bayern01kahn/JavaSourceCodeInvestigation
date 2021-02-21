package justin.algorithm.Leetcode.Q1117;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Semaphore_Solution {

    //volatile int hTag = 0;
    AtomicInteger hTag = new AtomicInteger(0);
    Semaphore hs = new Semaphore(2);
    Semaphore os = new Semaphore(1);


    public Semaphore_Solution () {

    }

    public static void main(String[] args) {
        Semaphore_Solution h2o = new Semaphore_Solution();
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
        hs.acquire();
        System.out.print("H");
        //hTag++;
        hTag.incrementAndGet();
        if (hTag.get() == 2) {
            os.release(1);
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        os.acquire();
        System.out.print("O");
        //hTag = 0;
        hTag.set(0);
        hs.release(2);
    }

}
