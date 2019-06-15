package justin.juc.exchanger;

import org.apache.log4j.Logger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 *  Exchanger是自jdk1.5起开始提供的工具套件，一般用于两个工作线程之间交换数据。
 *
 * A synchronization point at which threads can pair and swap elements within pairs. Each thread presents some object on entry to the exchange method, matches with a partner thread, and receives its partner's object on return. An Exchanger may be viewed as a bidirectional form of a SynchronousQueue. Exchangers may be useful in applications such as genetic algorithms and pipeline designs.
 *
 * 在以上的描述中，有几个要点：
 * 1.此类提供对外的操作是同步的；
 * 2.用于成对出现的线程之间交换数据；
 * 3.可以视作双向的同步队列；
 * 4.可应用于基因算法、流水线设计等场景。
 */
public class ExchangerTest {
    protected static final Logger log = Logger.getLogger(ExchangerTest.class);
    private static volatile boolean isDone = false;

    static class ExchangerProducer implements Runnable {
        private Exchanger<Integer> exchanger;
        private static int data = 1;
        ExchangerProducer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!Thread.interrupted() && !isDone) {
                for (int i = 1; i <= 3; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        data = i;
                        System.out.println("producer before: " + data);
                        data = exchanger.exchange(data);
                        System.out.println("producer after: " + data);
                    } catch (InterruptedException e) {
                        log.error(e, e);
                    }
                }
                isDone = true;
            }
        }
    }

    static class ExchangerConsumer implements Runnable {
        private Exchanger<Integer> exchanger;
        private static int data = 0;
        ExchangerConsumer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!Thread.interrupted() && !isDone) {
                data = 0;
                System.out.println("consumer before : " + data);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = exchanger.exchange(data);
                } catch (InterruptedException e) {
                    log.error(e, e);
                }
                System.out.println("consumer after : " + data);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<Integer> exchanger = new Exchanger<Integer>();
        ExchangerProducer producer = new ExchangerProducer(exchanger);
        ExchangerConsumer consumer = new ExchangerConsumer(exchanger);
        exec.execute(producer);
        exec.execute(consumer);
        exec.shutdown();
        try {
            exec.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e, e);
        }
    }
}
