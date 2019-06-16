package justin.juc.lock.stampedLock;

import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;


/**
 * 一篇不同需求的 性能测试 synchronized RWLock Stamped optimistic 对比:
 *   https://blog.overops.com/java-8-stampedlocks-vs-readwritelocks-and-synchronized/
 *
 */
public class StampedLockTest1 {

    private final static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = Lists.newArrayList();

    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };

        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
    }

    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(
                    DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
            ).ifPresent(System.out::println);
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    /**
     * 优化版本 乐观读
     */
    private static void optimisticRead() {
        long stamped = lock.tryOptimisticRead();
        if (lock.validate(stamped)) {
            try {
                stamped = lock.readLock();
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
                ).ifPresent(System.out::println);
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamped);
            }
        }
    }

    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            System.out.println("Writing!!!!");
            DATA.add(System.currentTimeMillis());
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }

}
