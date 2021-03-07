package justin.algorithm.Leetcode.Q1226;

import java.util.concurrent.Semaphore;

public class TheBest_solution {

    private volatile Semaphore semaphore = new Semaphore(3); // 用于协同至少有一个哲学家能吃上饭；
    private volatile Semaphore[] forksS = {new Semaphore(1), new Semaphore(1), new Semaphore(1),
            new Semaphore(1), new Semaphore(1)}; // 每个叉子的状态；

    private int n = 0;
    public TheBest_solution(int n) {
        this.n = n;
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        while (true) {
            if (forksS[philosopher].tryAcquire(1)) {
                if (forksS[(philosopher + 1) % 5].tryAcquire(1)) {
//                    pickLeftFork.run();
//                    pickRightFork.run();
//                    eat.run();
//                    putLeftFork.run();
//                    putRightFork.run();

                    System.out.println("哲学家"+philosopher+" 拿起了左边的叉子");
                    System.out.println("哲学家"+philosopher+" 拿起了右边的叉子");
                    System.out.println("哲学家"+philosopher+" 吃面");
                    System.out.println("哲学家"+philosopher+" 放下了左边的叉子");
                    System.out.println("哲学家"+philosopher+" 放下了右边的叉子");

                    forksS[philosopher].release();
                    forksS[(philosopher + 1) % 5].release();
                    semaphore.release();
                    break;
                } else {
                    forksS[philosopher].release();
                }
            }

            semaphore.acquire();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 4; i++) {
            long startTime = System.currentTimeMillis();
            test(i);
            long endTime = System.currentTimeMillis();
            System.out.println(i + " : " + (endTime - startTime));
        }
    }

    private static void test(int n) throws InterruptedException {
        TheBest_solution dp = new TheBest_solution(n);
        Runnable wait = () -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        dp.wantsToEat(n, wait, wait, wait, wait, wait);
    }
}
