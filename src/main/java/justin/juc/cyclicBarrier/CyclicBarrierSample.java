package justin.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * CountDownLatch  vs  CyclicBarrier
 *
 * 1.CyclicBarrier可以多次使用，CountDownLatch只能用一次（为0后不可变）
 * 2.Barrier是等待指定数量线程到达再继续处理；Latch是等待指定事件变为指定状态后发生再继续处理
 * 3.Barrier是等待指定数量任务完成，Latch是等待其他任务完成指定状态的改变再继续
 *
 * CountDownLatch是不可重置的，所以无法重用；而CyclicBarrier则没有这种限制，可以重用。
 * CountDownLatch的基本操作组合是countDown/await。调用await的线程阻塞等待countDown足够的次数，不管你是在一个线程还是多个线程里countDown，只要次数足够即可。
 * 所以说CountDownLatch操作的是事件。
 * CyclicBarrier的基本操作组合，则就是await。当所有的伙伴（parties）都调用了await，才会继续进行任务，并自动进行重置。
 *
 * 注意，正常情况下，CyclicBarrier的重置都是自动发生的，如果我们调用reset方法，但还有线程在等待，就会导致等待线程被打扰，抛出BrokenBarrierException异常。
 * CyclicBarrier侧重点是线程，而不是调用事件，它的典型应用场景是用来等待并发线程结束。
 *
 */
public class CyclicBarrierSample {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("Action...GO again!"));
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
    }

    static class CyclicWorker implements Runnable {
        private CyclicBarrier barrier;
        public CyclicWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Executed!");
                    barrier.await();
                }
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
