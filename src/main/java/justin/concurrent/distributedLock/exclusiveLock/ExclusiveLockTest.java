package justin.concurrent.distributedLock.exclusiveLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import justin.concurrent.distributedLock.DistributedLock;

/**
 * Created by code4wt on 17/8/24.
 */
public class ExclusiveLockTest {

    @Test
    public void lock() throws Exception {
    	System.out.println("进入lock");
    	
        Runnable runnable = () -> {
            try {
                DistributedLock lock = new ExclusiveLock();
                lock.lock();
                Thread.sleep(2000);
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        int poolSize = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.submit(runnable);
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

//    @Test
//    public void tryLock() throws Exception {
//    	System.out.println("进入 trylock");
//    	
//        ExclusiveLock lock = new ExclusiveLock();
//        Boolean locked = lock.tryLock();
//        System.out.println("trylock locked: " + locked);
//        Thread.sleep(1000);
//        lock.unlock();
//    }

//    @Test
//    public void tryLock1() throws Exception {
//    	System.out.println("进入 trylock-n");
//    	
//        ExclusiveLock lock = new ExclusiveLock();
//        Boolean locked = lock.tryLock(2000);
//        System.out.println("trylock1 locked: " + locked);
//        
//        System.out.println("进入 trylock-n2");
//        ExclusiveLock lock2 = new ExclusiveLock();
//        Boolean lock2ed = lock2.tryLock(1000);
//        System.out.println("trylock2 locked: " + lock2ed);
//        
//        System.out.println("进入 trylock-n3");
//        lock.unlock();
//        ExclusiveLock lock3 = new ExclusiveLock();
//        Boolean lock3ed = lock3.tryLock(2000);
//        System.out.println("trylock3 locked: " + lock3ed);
//    }

//    @Test
//    public void unlock() throws Exception {
//    }
}