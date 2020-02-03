package justin.juc.atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;


/**
 * 防止多线程竞争,
 * LongAdder,只是每次对给定的整数执行一次加法.
 * LongAccumulator, 则可以实现任意的函数操作.  这里就是 获取最大值
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];

        for(int i=0;i<1000;i++){
            ts[i] = new Thread(()->{
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }
        for(int i=0;i<1000;i++){
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}