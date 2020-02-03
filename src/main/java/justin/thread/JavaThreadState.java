package justin.thread;

import java.util.concurrent.TimeUnit;

public class JavaThreadState
{
    public static void main(String[] args)
    {

        Thread waittingBlocked = new Thread(new WaitingBlocked(),
                "waittingBlocked");

        Thread timeWaittingBlocked = new Thread(new TimeWaitBlocked(),
                "timeWaittingBlocked");

        // synchronizedBlocked1,synchronizedBlocked2抢同一把锁，只有一个可以抢到
        Thread synchronizedBlocked1 = new Thread(new SynchronizedBlocked(),
                "synchronizedBlocked1");

        Thread synchronizedBlocked2 = new Thread(new SynchronizedBlocked(),
                "synchronizedBlocked2");
        // sleepBlocked线程睡了100秒，在这一百秒中，其处于阻塞状态
        Thread sleepBlocked = new Thread(new sleepBlocked(), "sleepBlocked");

        // 获取主线程
        Thread mainTherad = Thread.currentThread();
        // 主线程调用join方法，只有joinBlocked线程死亡之后，主线程才会从阻塞中返回
        Thread joinBlocked = new Thread(new JoinBlocked(mainTherad),
                "joinBlocked");

        waittingBlocked.start();

        timeWaittingBlocked.start();

        synchronizedBlocked1.start();

        synchronizedBlocked2.start();

        sleepBlocked.start();

        joinBlocked.start();

    }
}

/**
 * 等待阻塞，一直处于等待状态，知道有其他线程将其唤醒
 * 
 * @author xiaotao
 * 
 */
class WaitingBlocked implements Runnable
{

    public void run()
    {
        synchronized (WaitingBlocked.class)
        {
            try
            {
                //调用wait()方法时线程会放弃对象锁
                WaitingBlocked.class.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}

/**
 * 在五十秒内处于等待状态，过了五十秒之后会被唤醒，进入runnable状态
 * 
 * @author xiaotao
 * 
 */
class TimeWaitBlocked implements Runnable
{

    public void run()
    {
        try
        {
            synchronized (TimeWaitBlocked.class)
            {
                TimeWaitBlocked.class.wait(50000);
                System.out
                        .println("l have wait about 50 s,so i don't want to wait");
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

/**
 * 该实例是一个抢锁的阻塞实例，两个线程争抢一个锁，只有一个线程抢到锁，另外一个线程抢不到锁，处于阻塞状态
 * 
 * @author xiaotao
 * 
 */
class SynchronizedBlocked implements Runnable
{

    public void run()
    {
        synchronized (SynchronizedBlocked.class)
        {
            while (true)
            {

            }
        }
    }

}

/**
 * 处于sleep阻塞状态，睡了100秒之后处于runnable状态，之后争抢cpu。
 * 
 * @author xiaotao
 * 
 */
class sleepBlocked implements Runnable
{

    public void run()
    {
        try
        {
            while (true)
            {
                TimeUnit.SECONDS.sleep(100);
                System.out.println("l have sleep 100 s");
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

class JoinBlocked implements Runnable
{
    private Thread thread;


    public JoinBlocked(Thread thread)
    {
        this.thread = thread;
    }


    public void run()
    {
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        while (true)
        {

        }

    }
}
