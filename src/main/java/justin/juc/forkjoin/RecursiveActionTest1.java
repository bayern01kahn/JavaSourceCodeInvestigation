package justin.juc.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


/**
 * 无结果情况 使用  RecursiveAction
 */
public class RecursiveActionTest1 {

    private final static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        final ForkJoinPool pool = new ForkJoinPool();

        pool.submit(new CalculateRecusiveAction(0,10));

        pool.awaitTermination(1, TimeUnit.SECONDS);

        Optional.of(SUM).ifPresent(System.out::println);

    }

    private static class CalculateRecusiveAction extends RecursiveAction{

        private final int Max_THRESHOLD = 5;
        private final int start;
        private final int end;

        public CalculateRecusiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }



        @Override
        protected void compute() {
            int mid = end-start;
            if(mid <= Max_THRESHOLD){
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            }else {
                mid = (start+end)/2;
                CalculateRecusiveAction leftAction = new CalculateRecusiveAction(start, mid);
                CalculateRecusiveAction rightAction = new CalculateRecusiveAction(mid+1, end);

                leftAction.fork();
                rightAction.fork();
            }
        }
    }
}
