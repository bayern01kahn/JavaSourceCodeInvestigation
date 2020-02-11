package justin.juc.threadPool;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class Run implements Runnable {

    private int index;

    public Run(int index) {
        this.index = index+1;
    }

    @Override
    public void run() {
        System.out.println("--"+Thread.currentThread().getName()+"开始运行 任务"+index);
        try {
            int waitTime = 100 + index * 10;
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======="+Thread.currentThread().getName()+"结束 任务"+index);
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
        return new Thread(r, "线程"+(++sequenceNumber));
    }
}

public class ThreadPoolExecutorTest1 {



    public static void main(String[] args) {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 6; i++) {
            service.execute(new Run(i));
        }
    }

    @Test
    public void normalRunWithOutShutDown() throws InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 6; i++) {
            service.execute(new Run(i));
        }
        //Thread.currentThread().setDaemon(true);
    }

    @Test
    public void normalRun() throws InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();

        // 固定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(3, threadFactory);
//        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
//        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        for (int i = 0; i < 6; i++) {
            service.execute(new Run(i));
        }

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

        for (int i = 0; i < 6; i++) {
            service.execute(new Run_Exception(i));
        }

        //使用shutdownNow方法，在任务队列中等待的任务是不会执行的，而且立即发起线程中断请求
        service.shutdownNow();

    }

}
