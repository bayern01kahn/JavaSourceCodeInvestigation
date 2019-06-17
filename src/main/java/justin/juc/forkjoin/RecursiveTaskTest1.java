package justin.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * 需要每一步的执行结果的情况下
 */
public class RecursiveTaskTest1 {

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        try {
            Integer result = forkJoinPool.submit(new ForkJoinRecursiveTask(0,10000)).get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private static class ForkJoinRecursiveTask extends RecursiveTask<Integer>{

        private final int Max_THRESHOLD = 5;

        private final int start;
        private final int end;

        public ForkJoinRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int mid = end-start;
            if(mid <= Max_THRESHOLD){
                return IntStream.rangeClosed(start, end).sum();
            }else {
                mid = (start+end)/2;
                ForkJoinRecursiveTask leftTask = new ForkJoinRecursiveTask(start, mid);
                ForkJoinRecursiveTask rightTask = new ForkJoinRecursiveTask(mid+1, end);

                leftTask.fork();
                rightTask.fork();

                return leftTask.join()+rightTask.join();
            }
        }
    }
}
