package justin.juc.stampedLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;


/**
 * https://www.cnblogs.com/zxporz/p/11642176.html
 * 可以看到相比直接用悲观读锁，乐观读锁可以：
 *
 * 1、进入悲观读锁前先看下有没有进入写模式（说白了就是有没有已经获取了悲观写锁）
 *
 * 2、如果其他线程已经获取了悲观写锁，那么就只能老老实实的获取悲观读锁（这种情况相当于退化成了读写锁）
 *
 * 3、如果其他线程没有获取悲观写锁，那么就不用获取悲观读锁了，减少了一次获取悲观读锁的消耗和避免了因为读锁
 *    导致写锁阻塞的问题，直接返回读的数据即可（必须再tryOptimisticRead和validate之间获取好数据，否则
 *    数据可能会不一致了，试想如果过了validate再获取数据，这时数据可能被修改并且读操作也没有任何保护措施）
 */
public class StampedLockTest1 {

    static ExecutorService service = Executors.newFixedThreadPool(10);
    static StampedLock lock = new StampedLock();
    static long milli = 5000;
    static int count = 0;

    private static long writeLock() {
        long stamp = lock.writeLock(); //获取排他写锁
        count += 1;
        lock.unlockWrite(stamp); //释放写锁
        System.out.println("数据写入完成");
        return System.currentTimeMillis();
    }

    private static void readLock() {//悲观读锁
        service.submit(() -> {
            int currentCount = 0;
            long stamp = lock.readLock(); //获取悲观读锁
            try {
                currentCount = count; //获取变量值
                try {
                    TimeUnit.MILLISECONDS.sleep(milli);//模拟读取需要花费20秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlockRead(stamp); //释放读锁
            }
            System.out.println("readLock==" + currentCount); //显示最新的变量值
        });
        try {
            TimeUnit.MILLISECONDS.sleep(500);//要等一等读锁先获得
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void optimisticRead() {
        service.submit(() -> {
            long stamp = lock.tryOptimisticRead(); //尝试获取乐观读锁
            int currentCount = count; //获取变量值
            if (!lock.validate(stamp)) { //判断count是否进入写模式
                stamp = lock.readLock(); //已经进入写模式，没办法只能老老实实的获取读锁
                try {
                    currentCount = count; //成功获取到读锁，并重新获取最新的变量值
                } finally {
                    lock.unlockRead(stamp); //释放读锁
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(milli);//模拟读取需要花费20秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //走到这里，说明count还没有被写，那么可以不用加读锁，减少了读锁的开销
            System.out.println("optimisticRead==" + currentCount); //显示最新的变量值
        });
        try {
            TimeUnit.MILLISECONDS.sleep(500);//要等一等读锁先获得
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //对于悲观读和悲观写的方法与ReentranReadWriteLock读写锁效果一样
//        long l1 = System.currentTimeMillis();
//        readLock();
//        long l2 = writeLock();
//        System.out.println(l2 - l1);




        //对于乐观读（如果没有进入写模式）可以减少一次读锁的性能消耗，并且不会阻塞写入的操作
        long l1 = System.currentTimeMillis();
        optimisticRead();
        long l2 = writeLock();
        System.out.println(l2-l1);
    }

}
