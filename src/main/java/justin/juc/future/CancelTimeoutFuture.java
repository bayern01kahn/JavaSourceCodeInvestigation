package justin.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CancelTimeoutFuture {

    ExecutorService taskExec = Executors.newFixedThreadPool(1);


    public static void main(String[] args) throws InterruptedException {
        CancelTimeoutFuture toc =new CancelTimeoutFuture();

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
                System.out.println("still runing after 5s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        long t1 = System.currentTimeMillis();
        toc.timedRun(r, 3, TimeUnit.SECONDS);
        long t2 = System.currentTimeMillis()-t1;
        System.out.println("time cost: "+ t2);
    }



    public  void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException
    {
        Future task=taskExec.submit(r);
        try{
            task.get(timeout,unit);
        } catch (TimeoutException e){
            //下面任务会被取消
            System.out.println("TimeoutException: "+ e.getStackTrace());
        } catch (ExecutionException e){
            //task中抛出的异常；重抛出
            System.out.println("ExecutionException: "+ e.getStackTrace());
        }
        finally{
            //如果任务已经结束，是无害的
            task.cancel(true);
            System.out.println("任务超时后被取消掉");
        }
    }

}
