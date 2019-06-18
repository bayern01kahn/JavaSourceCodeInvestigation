package justin.juc.scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit);
 提交一个Runnable任务延迟了initialDelay时间后，开始周期性的执行该任务，每period时间执行一次
 如果任务异常则退出。如果取消任务或者关闭线程池，任务也会退出。
 如果任务执行一次的时间大于周期时间，则任务执行将会延后执行，而不会并发执行

 */
public class ScheduleAtFixedRateT1 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run "+ System.currentTimeMillis());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);  //立刻执行，而且每隔100毫秒执行一次。
    }
}
