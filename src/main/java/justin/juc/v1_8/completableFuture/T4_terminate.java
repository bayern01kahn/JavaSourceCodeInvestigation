package justin.juc.v1_8.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T4_terminate {

    public static void main(String[] args) throws InterruptedException {

        //getNow();

        complete();
        //complete2();
        Thread.currentThread().join();
    }

    private static void complete() {
        boolean flag = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("in progress...");
            return "Hello";
        }).complete("World");

        System.out.println(flag);
    }

    private static void complete2() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("in progress...");
            return "Hello";
        });
        sleep(1);
        boolean flag =future.complete("World");
        System.out.println(flag);
    }

    private static void getNow() {
        String result = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            return "Hello";
        }).getNow("World");
        System.out.println(result);
    }


    private static void sleep(int value)  {
        try {
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
