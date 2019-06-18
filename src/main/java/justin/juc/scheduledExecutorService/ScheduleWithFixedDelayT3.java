package justin.juc.scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用scheduleWithFixedDelay()方法实现周期性执行
 *
 *
 *  scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit);
 *  提交一个Runnable任务延迟了initialDelay时间后，开始周期性的执行该任务，以后
 *  每两次任务执行的间隔是delay
 *  如果任务异常则退出。如果取消任务或者关闭线程池，任务也会退出。
 */
public class ScheduleWithFixedDelayT3 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println("          x = " + System.currentTimeMillis());
        executorService.scheduleWithFixedDelay(new MyRunable(), 1, 2, TimeUnit.SECONDS);
        System.out.println("          y = " + System.currentTimeMillis());
    }

    static class MyRunable implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("     begin = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(4);
                System.out.println("     end = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
