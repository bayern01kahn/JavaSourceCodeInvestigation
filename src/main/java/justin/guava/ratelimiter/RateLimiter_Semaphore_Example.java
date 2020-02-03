package justin.guava.ratelimiter;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;


public class RateLimiter_Semaphore_Example {
    //Guava  0.5的意思是 1秒中0.5次的操作，2秒1次的操作  从速度来限流，从每秒中能够执行的次数来
    private final static RateLimiter limiter=RateLimiter.create(0.5d);


    //同时只能有三个线程工作 Java1.5  从同时处理的线程个数来限流
    private final static Semaphore sem=new Semaphore(3);
    private static void testSemaphore(){
        try {
            sem.acquire();
            System.out.println(currentThread().getName()+" is doing work...");
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            sem.release();
            System.out.println(currentThread().getName()+" release the semephore..other thread can get and do job");
        }
    }

    public static void runTestSemaphore(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach((i)->{
            //RateLimiter_Semaphore_Example::testLimiter 这种写法是创建一个线程
            service.submit(RateLimiter_Semaphore_Example::testSemaphore);
        });
    }

    /**
     * Guava的RateLimiter
     */
    private static void testLimiter(){
        System.out.println(currentThread().getName()+" waiting  " +limiter.acquire());
    }

    //Guava的RateLimiter
    public static void runTestLimiter(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach((i)->{
            //RateLimiter_Semaphore_Example::testLimiter 这种写法是创建一个线程
            service.submit(RateLimiter_Semaphore_Example::testLimiter);
        });
    }

    public static void main(String[] args) {
        IntStream.range(0,10).forEach((a)-> System.out.println(a));//从0-9
        runTestLimiter();
        //runTestSemaphore();
    }

}

