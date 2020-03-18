package justin.juc.threadPool.custom;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 在线程创建工厂中 自定义 异常处理的类
 *
 *
 * 需要注意的是使用UncaughtExceptionHandler的方法只适用于execute方法执行的任务，而对submit方法是无效。
 *
 * submit执行的任务，可以通过返回的Future对象的get方法接收抛出的异常，再进行处理。
 */
public class JThreadPool1 {

    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d")
                .setUncaughtExceptionHandler(new LogUncaughtExceptionHandler())
                .build();

        ExecutorService pool = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(5), namedThreadFactory, new JCallerBlocksPolicy(1));

        IntStream.rangeClosed(0,100).forEach(i -> {
                    pool.execute(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        throw new RuntimeException("测试异常");
                    });
        }
        );


        pool.shutdown();
    }

    static class  LogUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("打印LogUncaughtExceptionHandler中获得的异常信息:" + e.getMessage());
        }
    }
}
