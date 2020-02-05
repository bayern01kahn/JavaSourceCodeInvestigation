package justin.guava.ratelimiter;


import com.google.common.util.concurrent.RateLimiter;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApiCallDemo {

    private int permitsPerSecond = 2; //每秒2个许可
    private int threadNum = 10;

    public static void main(String[] args) {

        new ApiCallDemo().call();
    }

    private void call() {

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
        for (int i=0; i<threadNum; i++) {
            //executor.execute(new ApiCallTask(rateLimiter));       //匀速生成令牌
            executor.execute(new BurstyApiCallTask(rateLimiter));   //预消费模式, 处理突发流量的实现方式.
        }

        executor.shutdown();
    }
}

class ApiCallTask implements Runnable{
    private RateLimiter rateLimiter;
    private boolean runing = true;
    public ApiCallTask(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {

        while(runing){
            rateLimiter.acquire(); // may wait
            getData();
        }
    }

    /**模拟调用合作伙伴API接口*/
    private void getData(){
        System.out.println(Thread.currentThread().getName()+" runing!");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class BurstyApiCallTask implements Runnable{
    private RateLimiter rateLimiter;
    private boolean runing = true;
    public BurstyApiCallTask(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    int min =1;
    int max =3;
    Random random = new Random();
    int token = random.nextInt(max)%(max-min+1) + min;


    @Override
    public void run() {
        System.out.println("随机token 为: "+ token);
        while(runing){
            rateLimiter.acquire(token); // may wait    //获取1次多个令牌
            getData(token);
        }
    }

    /**模拟调用合作伙伴API接口*/
    private void getData(int token){
        System.out.println(Thread.currentThread().getName()+" runing!  and get token: "+ token);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}