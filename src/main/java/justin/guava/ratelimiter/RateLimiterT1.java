package justin.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RateLimiterT1 {

    public static void main(String[] args) {
        RateLimiterT1 t1 = new RateLimiterT1();

        //平滑突发限流
//        t1.testSmoothBursty();
//        t1.testSmoothBursty2();
//        t1.testSmoothBursty3();

        //平滑预热限流
        t1.testSmoothwarmingUp();

        //IntStream.range(0,10).forEach(i -> System.out.println(i));
    }


    public void testSmoothBursty() {
        RateLimiter r = RateLimiter.create(5);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
        /**
         * output: 基本上都是0.2s执行一次，符合一秒发放5个令牌的设定。
         * get 1 tokens: 0.0s
         * get 1 tokens: 0.182014s
         * get 1 tokens: 0.188464s
         * get 1 tokens: 0.198072s
         * get 1 tokens: 0.196048s
         * get 1 tokens: 0.197538s
         * get 1 tokens: 0.196049s
         */
    }
    // RateLimiter使用令牌桶算法，会进行令牌的累积，如果获取令牌的频率比较低，则不会导致等待，直接获取令牌。
    public void testSmoothBursty2() {
        RateLimiter r = RateLimiter.create(2);
        while (true)
        {
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
            /**
             * output:
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * end
             * get 1 tokens: 0.499796s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             * get 1 tokens: 0.0s
             */
        }
    }

    //RateLimiter由于会累积令牌，所以可以应对突发流量。在下面代码中，有一个请求会直接请求5个令牌，但是由于此时令牌桶中有累积的令牌，足以快速响应。
    // RateLimiter在没有足够令牌发放时，采用滞后处理的方式，也就是前一个请求获取令牌所需等待的时间由下一次请求来承受，也就是代替前一个请求进行等待。
    public void testSmoothBursty3() {
        RateLimiter r = RateLimiter.create(5);
        while (true)
        {
            System.out.println("get 5 tokens: " + r.acquire(5) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
            /**
             * output:
             * get 5 tokens: 0.0s
             * get 1 tokens: 0.996766s 滞后效应，需要替前一个请求进行等待
             * get 1 tokens: 0.194007s
             * get 1 tokens: 0.196267s
             * end
             * get 5 tokens: 0.195756s
             * get 1 tokens: 0.995625s 滞后效应，需要替前一个请求进行等待
             * get 1 tokens: 0.194603s
             * get 1 tokens: 0.196866s
             */
        }
    }

    //SmoothWarmingUp是带有预热期的平滑限流，它启动后会有一段预热期，逐步将分发频率提升到配置的速率。
    //
    // 比如下面代码中的例子，创建一个平均分发令牌速率为2，预热期为10s。由于设置了预热时间是10秒，令牌桶一开始并不会0.5秒发一个令牌，
    // 而是形成一个平滑线性下降的坡度，频率越来越高，在10秒钟之内达到原本设置的频率，以后就以固定的频率输出。这种功能适合系统刚启动需要一点时间来“热身”的场景
    public void testSmoothwarmingUp() {
        RateLimiter r = RateLimiter.create(2, 10, TimeUnit.SECONDS);
        while (true)
        {
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
            /**
             * output:
             * get 1 tokens: 0.0s
             * get 1 tokens: 1.448344s
             * get 1 tokens: 1.346713s
             * get 1 tokens: 1.245611s
             * get 1 tokens: 1.14668s
             * get 1 tokens: 1.047927s
             * end
             * get 1 tokens: 0.944627s
             * get 1 tokens: 0.847485s
             * get 1 tokens: 0.749651s
             * get 1 tokens: 0.646277s
             * get 1 tokens: 0.549972s
             * get 1 tokens: 0.496281s   <---  10s.后 达到稳定的速率
             * end
             * get 1 tokens: 0.496095s
             * get 1 tokens: 0.497385s
             * get 1 tokens: 0.499697s
             * get 1 tokens: 0.497821s
             * get 1 tokens: 0.498647s
             * get 1 tokens: 0.496289s
             * end
             */
        }
    }
}
