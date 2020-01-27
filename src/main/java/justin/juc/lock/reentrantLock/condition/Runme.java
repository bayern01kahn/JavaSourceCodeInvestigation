package justin.juc.lock.reentrantLock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class Runme
{
    public static void main( String[] args )
    {
        Lock lock=new ReentrantLock(); //重入锁
        Condition condition=lock.newCondition();

        Thread t1 = new Thread(new ConditionAwait(lock,condition));
        Thread t2 = new Thread(new ConditionNotify(lock,condition));

        //step 1
        t1.start();// 阻塞await
        //step 2
        t2.start();//
    }
}
