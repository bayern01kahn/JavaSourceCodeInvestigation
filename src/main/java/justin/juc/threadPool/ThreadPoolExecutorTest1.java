package justin.juc.threadPool;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Run implements Runnable {

    private int index;
    private ThreadPoolExecutor tpe;

    public Run(int index) {
        this.index = index+1;
    }

    public Run(int index,ThreadPoolExecutor tpe) {
        this.index = index+1;
        this.tpe = tpe;
    }

    @Override
    public void run() {
        String tName = Thread.currentThread().getName();
        System.out.println(">>"+tName+" 开始运行 任务"+index + "\t线程池当前活跃线程数"+ tpe.getActiveCount()+ "\t已执行完成任务数"+ tpe.getCompletedTaskCount());
        try {
            int waitTime = 100 + index * 100;
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("<<"+tName+" 结束运行 任务"+index + "\t线程池当前活跃线程数"+ tpe.getActiveCount()+ "\t已执行完成任务数"+ tpe.getCompletedTaskCount());
    }
}

class Run_Exception implements Runnable {

    private int index;

    public Run_Exception(int index) {
        this.index = index+1;
    }

    @Override
    public void run() {
        System.out.println("--"+Thread.currentThread().getName()+"开始运行 任务"+index);
        try {
            int waitTime = 100 + index * 10;
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" 发生中断异常  exception=="+e.getMessage());
        }
        System.out.println("======="+Thread.currentThread().getName()+"结束 任务"+index);
    }
}

class MyThreadFactory implements ThreadFactory {
    private int sequenceNumber = 0;

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "T"+(++sequenceNumber));
    }
}

@Slf4j
public class ThreadPoolExecutorTest1 {



    public static void main(String[] args) {
    }

    @Test
    public void normalRunWithOutShutDown() throws InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 10; i++) {
            service.execute(new Run(i));
        }
        Thread.sleep(2000);
    }

    @Test
    public void normalRun() throws InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 10; i++) {
            service.execute(new Run(i));
        }


        Thread.sleep(200);
        // 使用shutdown方法，还是会执行完已经添加的任务。最后程序退出
        service.shutdown();
    }


    @Test
    public void RunException() throws InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 10; i++) {
            service.execute(new Run_Exception(i));
        }

        //使用shutdownNow方法，在任务队列中等待的任务是不会执行的，而且立即发起线程中断请求
        service.shutdownNow();

    }

    @Test
    public void customPool(){
        ThreadFactory threadFactory = new MyThreadFactory();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), threadFactory,
                new JustinAbortPolicy());
        //new ThreadPoolExecutor.CallerRunsPolicy());
                //new ThreadPoolExecutor.DiscardOldestPolicy());
                //new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new Run(i,threadPoolExecutor));
        }

        try {
            threadPoolExecutor.shutdown();


            if(!threadPoolExecutor.awaitTermination(20,TimeUnit.SECONDS)){//
                System.out.println(" 到达指定时间，还有线程没执行完，不再等待，关闭线程池!");
                threadPoolExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程池最大峰值数 "+ threadPoolExecutor.getLargestPoolSize());
            threadPoolExecutor.shutdownNow();
        }
    }


    public static class JustinAbortPolicy implements RejectedExecutionHandler {

        public JustinAbortPolicy() { }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.info("Justin 自定义拒绝策略方式");
            log.warn("活跃数:"+ e.getActiveCount()+" 最大峰值:"+ e.getLargestPoolSize()+" 完成数:"+ e.getCompletedTaskCount());
        }
    }
}
