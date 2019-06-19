package justin.juc.v1_8.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T3_compose {

    public static void main(String[] args) throws InterruptedException {
//        thenAcceptBoth();
//        Thread.currentThread().join();
        
//        acceptEither();
//        Thread.currentThread().join();

//        runAfterBoth();
//        Thread.currentThread().join();

//        runAfterEither();
//        Thread.currentThread().join();

//        combine();
//        Thread.currentThread().join();

        compose();
        Thread.currentThread().join();
    }

    private static void compose() {
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the compose1");
            sleep(3);
            System.out.println("end the compose1");
            return "compose1";
        })).thenCompose(s-> CompletableFuture.supplyAsync(()->{
            System.out.println("start the compose2");
            sleep(2);
            System.out.println("end the compose2");
            return s+ "+ compose2";
        })).thenAccept(System.out::println);
    }

    private static void combine() {
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the combine1");
            sleep(3);
            System.out.println("end the combine1");
            return "combine1";
        })).thenCombine(CompletableFuture.supplyAsync(()->{
            System.out.println("start the combine2");
            sleep(2);
            System.out.println("end the combine2");
            return 100;
        }), (s,i)-> s.length()>i).whenComplete((v,t)-> System.out.println(v));

    }

    private static void runAfterEither() {
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the supplyAsync");
            sleep(3);
            System.out.println("end the supplyAsync");
            return "supplyAsync";
        })).runAfterEither(CompletableFuture.supplyAsync(()->{
            System.out.println("start the runAfterBothAsync");
            sleep(2);
            System.out.println("end the runAfterBothAsync");
            return 100;
        }), ()-> System.out.println("Done"));
    }

    private static void runAfterBoth() {
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the supplyAsync");
            sleep(3);
            System.out.println("end the supplyAsync");
            return "supplyAsync";
        })).runAfterBothAsync(CompletableFuture.supplyAsync(()->{
            System.out.println("start the runAfterBothAsync");
            sleep(2);
            System.out.println("end the runAfterBothAsync");
            return 100;
        }), ()-> System.out.println("Done"));

    }

    private static void acceptEither() {
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the supplyAsync");
            sleep(3);
            System.out.println("end the supplyAsync");
            return "supplyAsync";
        })).acceptEither(CompletableFuture.supplyAsync((()->{
            System.out.println("start the acceptEither");
            sleep(3);
            System.out.println("end the acceptEither");
            return "acceptEither2";
        })), System.out::println);

    }


    private static void thenAcceptBoth(){
        CompletableFuture.supplyAsync((()->{
            System.out.println("start the supplyAsync");
            sleep(3);
            System.out.println("end the supplyAsync");
            return "supplyAsync";
        })).thenAcceptBoth(CompletableFuture.supplyAsync(()->{
            System.out.println("start the thenAcceptBoth");
            sleep(3);
            System.out.println("end the thenAcceptBoth");
            return 100;
        }), (s,i)-> System.out.println(s+"--"+ i));
    }

    private static void sleep(int value)  {
        try {
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
