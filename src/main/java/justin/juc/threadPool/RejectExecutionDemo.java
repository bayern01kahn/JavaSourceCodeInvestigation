package justin.juc.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RejectExecutionDemo {


    /**
     *
     *
     * 当提交的任务数大于（workQueue.size() + maximumPoolSize ），就会触发线程池的拒绝策略
     *
     *
     */

    public static void main(String[] args) throws Exception{
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10);
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                                                             maximumPoolSize,
                                                             keepAliveTime,
                                                             TimeUnit.SECONDS,
                                                             workQueue,
                                                             handler);

        for(int i=0; i<100; i++) {
            try {
                executor.execute(new Thread(() -> log.info(Thread.currentThread().getName() + " is running")));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        executor.shutdown();
    }
}
