package justin.juc.completionService;

import jersey.repackaged.com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceT2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(5);
                    System.out.println("Thread 5 finished");
                    return 5;
                },
                () -> {
                    sleep(2);
                    System.out.println("Thread 2 finished");
                    return 2;
                },
                () -> {
                    sleep(3);
                    System.out.println("Thread 3 finished");
                    return 3;
                }
        );

        ExecutorCompletionService completionService = new ExecutorCompletionService(service);

        List<Future<Integer>> futures = Lists.newArrayList();
        callableList.stream().forEach(callable -> futures.add(completionService.submit(callable)));

        Future<Integer> future;
        while((future = completionService.take()) != null){
            System.out.println(future.get());
        }

        //System.out.println(completionService.poll(4, TimeUnit.SECONDS).get());
    }

    private static void sleep(int time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }


}
