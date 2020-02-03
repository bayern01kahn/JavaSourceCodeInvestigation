package justin.juc.completionService;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    //初始化线程池
    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Test
    public void test1(){
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int j = 1; j <= 5; j++) {

            final int index = j;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //第三个线程睡眠等待
                    if (index == 3) {
                        Thread.sleep(3000l);
                    }
                    return index;
                }
            });
        }
        threadPool.shutdown();

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("线程:"+completionService.take().get()+" 任务执行结束:");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}



