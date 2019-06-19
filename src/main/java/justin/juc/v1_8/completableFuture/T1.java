package justin.juc.v1_8.completableFuture;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class T1 {

    public static void main(String[] args)  throws InterruptedException{


        //oldWay();

        betterWay();
    }

    private static void betterWay()  throws InterruptedException{
        IntStream.range(0,10)
                //.boxed()
                .forEach( i -> CompletableFuture.supplyAsync(T1::prepareData)
                .thenAccept(T1::display)
                .whenComplete((v,t)-> System.out.println("Thread " + i + ": Done"))
        );

        /**
         * 因为 completableFuture 是使用的线程是守护线程 这句是为了控制在 main 在该方法完成后在结束
         * 如果不加这句, 会出现上面方法还未执行完 main就退出了
         */
        Thread.currentThread().join();
    }

    private static void oldWay() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> tasks = IntStream.range(0,10).boxed().
                map(i -> (Callable<Integer>) () -> prepareData()).collect(toList());

        executorService.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(T1::display);
    }

    private static int prepareData() {
        String threadName = Thread.currentThread().getName();
        int value = ThreadLocalRandom.current().nextInt(20);
        System.out.println(threadName + " prepareData() is working.");
        sleep(value);
        System.out.println(threadName + " prepareData() finished. Cost: " + value);
        return value;
    }

    private static void display(int data)  {
        String threadName = Thread.currentThread().getName();
        int value = ThreadLocalRandom.current().nextInt(5);
        System.out.println(threadName + " display() is working.");
        sleep(value);
        System.out.println(threadName + " display() finished. Cost: " + data);
    }

    private static void sleep(int value)  {
        try {
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
