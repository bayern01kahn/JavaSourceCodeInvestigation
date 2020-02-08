package justin.juc.completableFuture;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;


/**
 * https://www.jianshu.com/p/6bac52527ca4
 */
public class Demo_Java8 {

    public void waiting(long time){
        try {
            TimeUnit.SECONDS.sleep(time);
            System.out.println("等待"+ time + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    CompletableFuture<Integer> f1s;
    CompletableFuture<Integer> f3s;
    CompletableFuture<Integer> f5s;

    public void init(){
        f1s = CompletableFuture.supplyAsync(() -> {
            waiting(1);
            return 1;
        });

        f3s = CompletableFuture.supplyAsync(() -> {
            waiting(3);
            return 3;
        });

        f5s = CompletableFuture.supplyAsync(() -> {
            waiting(5);
            return 5;
        });
    }




    /**
     * 异步执行 无返回值
     */
    @Test
    public void runAsyncTest() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });
        future.get();
    }


    /**
     * 针对超时的处理建议使用另外的线程池,然后对线程池进行超时控制
     * Java9 已有专门的针对超时的 方法  见  Demo_Java9
     */
    @Test
    public void TimeOut() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println("Waiting 10s ...");
        }, executorService);
        future.get();

        if(!executorService.awaitTermination(3,TimeUnit.SECONDS)){
            System.out.println("超时机制启动,结束");
        }

    }

    /**
     * 异步执行 带返回值
     */
    @Test
    public void supplyAsync() throws Exception{
        init();
        long time = f1s.get();
        //long time = f3s.get();
        //long time = f5s.get();
        System.out.println("time = "+time);
    }


    /**
     * 计算结果完成时 回掉方法
     */
    @Test
    public void whenComplete() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        /**
         * 执行当前任务的线程继续执行 whenComplete的任务
         */
        //future.whenComplete((t, action) -> System.out.println("执行完成！"));
        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }

        });

        /**
         * 加上async 就会这个任务继续提交给forkjoinpool线程池来执行
         */
        future.whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }
        });

        //future.exceptionally(t -> { System.out.println("执行失败！"+t.getMessage()); return null; });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     * 第二个任务依赖第一个任务的结果。
     */
    @Test
    public void thenApply() throws Exception{
        init();
        f3s.thenApply(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                System.out.println("拿到第一个任务的结果: "+ t);
                int result = t*5;
                System.out.println("第二个任务计算后的结果="+result);
                return result;
            }
        });
        long result = f3s.get();
        System.out.println(result);


//        f3s.thenApply(t -> {
//            System.out.println("拿到第一个任务的结果: "+ t);
//            int result1 = t*5;
//            System.out.println("第二个任务计算后的结果="+ result1);
//            return result1;
//        });
//        long result = f3s.get();
//        System.out.println(result);
    }


    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。
     * 不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     */
    @Test
    public void handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int i= 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());

        /**
         * 从示例中可以看出，在 handle 中可以根据任务是否有异常来进行做相应的后续处理操作。  第一步已经抛出异常,但是会继续执行第二步
         * 而 thenApply 方法，如果上个任务出现错误，则不会执行 thenApply 方法。
         */
    }


    /**
     * 接收任务的处理结果，并消费处理，无返回结果。
     */
    @Test
    public void thenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();

        /**
         * 从示例代码中可以看出，该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。
         */
    }


    /**
     *  跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun 。
     */
    @Test
    public void thenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();

        /**
         * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。
         * 只是处理完任务后，执行 thenRun 的后续操作。
         */
    }


    /**
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     */
    @Test
    public void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                waiting(5);
                return "hello";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                waiting(1);
                return "world";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String t, String u) {
                return t+" - "+u;
            }
        });
        System.out.println(result.get());


        //  lambda  ==>

//        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "hello");
//        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> "world");
//        CompletableFuture<String> resultC = futureA.thenCombine(futureB, (t, u) -> t+" "+u);
//        System.out.println(resultC.get());
    }


    /**
     *  thenAcceptBoth  ?????????
     *  当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     */
    @Test
    public void thenAcceptBoth() throws Exception {
        init();
        final Integer[] value = {0};

        f1s.thenAcceptBoth(f3s, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer u) {
                value[0] = t + u;
                System.out.println("f1s="+t+";f3s="+u+";");
            }
        });

        System.out.println(value[0]);

//        f1s.thenAcceptBoth(f3s, (t, u) -> System.out.println("f1s="+t+";f3s="+u+";"));
    }

    @Test
    public void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));

        System.out.println(result);
    }


    /**
     * applyToEither 方法
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     */
    @Test
    public void applyToEither() throws Exception {
        init();
        CompletableFuture<Integer> result = f1s.applyToEither(f3s, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t;
            }
        });

        System.out.println(result.get());
    }


    /**
     * runAfterEither    ?????
     *  两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     */
    @Test
    public void runAfterEither() throws Exception {
        init();
        f1s.runAfterEither(f3s, new Runnable() {

            @Override
            public void run() {
                System.out.println("上面有一个已经完成了。");
            }
        });
    }

    /**
     * runAfterBoth   ????
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     */
    @Test
    public void runAfterBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                System.out.println("f1="+t);
                waiting(t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                System.out.println("f2="+t);
                waiting(t);
                return t;
            }
        });
        f1.runAfterBoth(f2, new Runnable() {

            @Override
            public void run() {
                System.out.println("上面两个任务都执行完成了。");
            }
        });
    }


    /**
     * thenCompose 方法    流水线串行操作
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
     */
    @Test
    public void thenCompose() throws Exception {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                System.out.println("第一个任务结果="+t);
                waiting(t);
                return t;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = param + 2;  //在第一个任务基础上计算
                        waiting(t);
                        System.out.println("第二个任务结果="+t);
                        return t;
                    }
                });
            }

        });
        System.out.println("thenCompose result : "+f.get());

        // ==>  lambda

//        CompletableFuture<Integer> fa = CompletableFuture.supplyAsync(() -> {
//            int t = new Random().nextInt(3);
//            System.out.println("t1="+t);
//            waiting(t);
//            return t;
//        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
//            int t = param + 2;
//            waiting(t);
//            System.out.println("t2="+t);
//            return t;
//        }));
//        System.out.println("thenCompose result : "+fa.get());
    }


    /**
     * anyof
     */
    @Test
    public void anyOf() {
        init();
        CompletableFuture<Object> future = CompletableFuture.anyOf(f1s, f3s, f5s);
        Object result = future.join();
        System.out.println("执行结束: "+ result);
        //就是只要有任意一个 CompletableFuture 实例执行完成, join 就会结束 所以这里需要1s
    }

    /**
     * allof
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs){...}
     *
     * 由于 allOf 聚合了多个 CompletableFuture 实例，所以它是没有返回值的。这也是它的一个缺点。
     *
     */
    @Test
    public void allOf(){
        init();
        CompletableFuture<Void> all = CompletableFuture.allOf(f1s, f3s, f5s);

        //阻塞，直到所有任务结束。
        all.join();
        //一个需要耗时5秒，一个需要耗时3秒，一个需要耗时1秒，只有当最长的耗时5秒的完成后，才会结束。
        System.out.println("任务均已完成。");
    }
}
