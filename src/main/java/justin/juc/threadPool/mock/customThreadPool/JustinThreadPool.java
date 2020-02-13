package justin.juc.threadPool.mock.customThreadPool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.common.base.Preconditions.checkNotNull;

public class JustinThreadPool implements JThreadPool {



    private final int capacity;
    private List<Worker> initWorkers;
    private Deque<Worker> availableWorkers;
    private Deque<Worker> busyWorkers;
    private final Object nextLock =new Object();


    public JustinThreadPool(int capacity) {
        this.capacity = capacity;
        init(capacity);
    }

    private void init(int capacity) {
        initWorkers = new ArrayList<>(capacity);
        availableWorkers = new LinkedList<>();
        busyWorkers = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            Worker worker = new Worker();
            worker.setName("Worker-"+ (i+1));
            worker.setDaemon(true);
            initWorkers.add(worker);
        };

        for (Worker w : initWorkers) {
            w.start();
            availableWorkers.add(w);
        }
    }


    @Override
    public void execute(Runnable runnable) {
        checkNotNull(runnable);

        synchronized (nextLock){
            while (availableWorkers.size() < 1) {
                try {
                    nextLock.wait(500);
                } catch (InterruptedException e) {
                    //ignore
                }
            }

            Worker worker = availableWorkers.removeFirst();
            busyWorkers.add(worker);
            worker.run(runnable);
            nextLock.notifyAll();
        }

    }

    private void makeAvailable(Worker worker) {
        synchronized (nextLock) {
            availableWorkers.add(worker);
            busyWorkers.remove(worker);
            nextLock.notifyAll();
        }
    }

    private class Worker extends Thread {
        private final Object lock = new Object();
        private Runnable runnable;
        private AtomicBoolean runing = new AtomicBoolean(true);

        private void run(Runnable runnable) {
            synchronized (lock) {
                if (null != this.runnable) {
                    throw new IllegalStateException("Already running a Runnable!");
                }
                this.runnable = runnable;
                lock.notifyAll();
            }
        }

        @Override
        public void run(){
            boolean ran = false;
            while(runing.get()) {
                try {
                    synchronized (lock){
                        while (runnable == null && runing.get()) {
                            lock.wait(500);
                        }

                        if (runnable != null) {
                            ran = true;
                            runnable.run();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    synchronized (lock) {
                        runnable = null;
                    }
                    if (ran) {
                        ran = false;
                        makeAvailable(this);
                    }
                }
            }
        }
    }

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception{
        JThreadPool threadPool = new JustinThreadPool(1);
        threadPool.execute(() -> {
            try {
                System.out.println(String.format("[%s]-任务一开始执行持续3秒...", LocalDateTime.now().format(F)));
                Thread.sleep(3000);
                System.out.println(String.format("[%s]-任务一执行结束...", LocalDateTime.now().format(F)));
            }catch (Exception e){
                //ignore
            }
        });
        threadPool.execute(() -> {
            try {
                System.out.println(String.format("[%s]-任务二开始执行持续4秒...", LocalDateTime.now().format(F)));
                Thread.sleep(4000);
                System.out.println(String.format("[%s]-任务二执行结束...", LocalDateTime.now().format(F)));
            }catch (Exception e){
                //ignore
            }
        });
        threadPool.execute(() -> {
            try {
                System.out.println(String.format("[%s]-任务三开始执行持续5秒...", LocalDateTime.now().format(F)));
                Thread.sleep(5000);
                System.out.println(String.format("[%s]-任务三执行结束...", LocalDateTime.now().format(F)));
            }catch (Exception e){
                //ignore
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
