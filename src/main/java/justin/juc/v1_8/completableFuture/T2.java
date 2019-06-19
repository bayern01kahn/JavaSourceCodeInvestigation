package justin.juc.v1_8.completableFuture;

import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class T2 {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {



//        supplyAsync();

//        runAsync();
//        Thread.currentThread().join();

//        System.out.println(">>>> " + anyof().get());
//        Thread.currentThread().join();

//        allof();
//        Thread.currentThread().join();

//        handleAsync();
//        handleAsyncWithException();

        /**
         * 不带 async 的 thenRun() 方法仍然是一个异步方法，可能是使用main线程，commonPool的线程或者是executor的线程。
         */
        //thenRunAsync();
        //testRunOnCommonPool();
        testRunOnExecutors();

    }

    private static void thenRunAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future.thenRun(() -> System.out.println("Done")).thenRunAsync(() -> System.out.println("AsyncDone"));
    }

    @Test
    public static  void testRunOnCommonPool() throws InterruptedException {

        CompletionStage<Void> futurePrice = CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("test1:1 - runAsync(runnable), job thread: " + Thread.currentThread());
                    //Thread[ForkJoinPool.commonPool-worker-1,5,main]
                }
        );

        System.out.println("test1:flag1");

        futurePrice.thenRun(() -> {
            System.out.println("test1:2 - thenRun(runnable)), action thread: " + Thread.currentThread());
            //Thread[ForkJoinPool.commonPool-worker-1,5,main]
        });

        System.out.println("test1:flag2");

        futurePrice.thenRunAsync(() -> {
            System.out.println("test1:3 - thenRunAsync(runnable), action thread: " + Thread.currentThread());
            //Thread[ForkJoinPool.commonPool-worker-1,5,main]
        });

        TimeUnit.SECONDS.sleep(100);

    }

    public static  void testRunOnExecutors() throws InterruptedException {
        CompletionStage<Void> futurePrice = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2:1 - runAsync(runnable, executor), job thread: " + Thread.currentThread());
            //Thread[pool-1-thread-1,5,main]
        }, executor);

        System.out.println("test2:flag1");

        futurePrice.thenRunAsync(() -> {
            System.out.println("test2:2 - thenRunAsync(runnable), action thread: " + Thread.currentThread());
            //Thread[pool-1-thread-1,5,main]
        });

        System.out.println("test2:flag2");

        futurePrice.thenRun(() -> {
            System.out.println("test2:3 - thenRun(runnable), action thread: " + Thread.currentThread());
            //Thread[pool-1-thread-2,5,main]
        });

        futurePrice.thenRunAsync(() -> {
            System.out.println("test2:4 - thenRunAsync(runnable, executor), action thread: " + Thread.currentThread());
            //Thread[ForkJoinPool.commonPool-worker-1,5,main]
        }, executor);

        TimeUnit.SECONDS.sleep(100);
    }


    private static void handleAsyncWithException() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
                (Supplier<String>) () -> {
                    throw new RuntimeException("no data");
                }).handleAsync((s, t) -> {
            Optional.of(t).ifPresent(e -> System.out.println("Error"));
            return (s == null) ? 0 : s.length();
        });
        System.out.println(future.get());
    }

    private static void handleAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> "Hello").handleAsync((s, t) -> s + " world");
        System.out.println(result.get());
    }

    private static void allof() {
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> getValue(3)).thenAccept(T2::sleepAndAdd).whenComplete((v, t) -> System.out.println("结束")),
                CompletableFuture.supplyAsync(() -> getValue(4)).thenAccept(T2::sleepAndAdd).whenComplete((v, t) -> System.out.println("结束")),
                CompletableFuture.supplyAsync(() -> getValue(5)).thenAccept(T2::sleepAndAdd).whenComplete((v, t) -> System.out.println("结束"))
        );
    }

    private static Future<?> anyof() {
        CompletableFuture<Object> future = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> getValue(3)).whenComplete((v, t) -> System.out.println("1结束")),
                CompletableFuture.supplyAsync(() -> getValue(4)).whenComplete((v, t) -> System.out.println("2结束")),
                CompletableFuture.supplyAsync(() -> getValue(5)).whenComplete((v, t) -> System.out.println("3结束"))
        );

        return future;
    }

    private static void supplyAsync() {

        CompletableFuture.supplyAsync(() -> getValue(3)).thenAccept(T2::sleepAndAdd)
                .runAfterBoth(CompletableFuture.supplyAsync(() -> getValue(5)).thenAccept(T2::sleepAndAdd), () -> System.out.println("第二个异步任务结束"))
                .whenComplete((v, t) -> System.out.println("整体结束"));

    }

    private static Integer getValue(int i) {
        return i;
    }

    private static int sleepAndAdd(int value) {
        try {
            System.out.println("Value is: " + value);
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value + value;
    }

    private static void runAsync() {
        CompletableFuture.runAsync(() -> sleepAndAdd(5)).whenComplete((v, t) -> System.out.println("Over!"));
    }


}
