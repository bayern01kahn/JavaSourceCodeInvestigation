package justin.juc.completableFuture;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Java 9 中新增的对超时处理的方法
 */
public class Demo_Java9 {

//    @Test
//    public void orTimeout() throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::computeEndlessly)
//                .orTimeout(1, TimeUnit.SECONDS);
//
//        future.get(); // java.util.concurrent.ExecutionException after waiting for 1 second
//    }
//
//
//    @Test
//    public void completeOnTimeout() throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::computeEndlessly)
//                .completeOnTimeout(42, 1, TimeUnit.SECONDS);
//
//        Integer result = future.get(); // 42
//    }


    private <U> U computeEndlessly() {

        return null;
    }
}
