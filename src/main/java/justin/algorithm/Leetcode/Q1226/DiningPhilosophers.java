package justin.algorithm.Leetcode.Q1226;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 这道题本质上其实是想考察如何避免死锁。
 * 易知：当 55 个哲学家都拿着其左边(或右边)的叉子时，会进入死锁。
 *
 * PS：死锁的 44 个必要条件：
 *
 * 互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
 * 请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
 * 不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。
 * 循环等待条件: 若干进程间形成首尾相接循环等待资源的关系。
 * 故最多只允许 44 个哲学家去持有叉子，可保证至少有 11 个哲学家能吃上意大利面（即获得到 22 个叉子）。
 * 因为最差情况下是：44 个哲学家都各自持有1个叉子，此时还 剩余 11 个叉子 可供使用，这 44 个哲学家中必然有1人能获取到这个 剩余的 11 个叉子，从而手持 22 个叉子，可以吃意大利面。
 * 即：44 个人中，11 个人有 22 个叉子，33 个人各持 11 个叉子，共计 55 个叉子。
 *
 * 既然最多只允许4个哲学家去持有叉子，那么如果只允许3个哲学家去持有叉子是否可行呢？
 *
 * 当然可行，33个哲学家可以先都各自持有11把叉子，此时还剩余22把叉子；
 *
 * 当这33个哲学家刚好都相邻(比如：编号为图中的0, 1, 2)，可能会造成只有11个哲学家能吃到意面的情况，具体而言即0号哲学家拿到了其左侧的叉子(编号为1)，1号哲学家也拿到了其左侧的叉子(编号为2)，2号哲学家也拿到了其左侧的叉子(编号为3)，此时只有0号哲学家能拿到其右侧的叉子(编号为0)，因此只有0号哲学家能吃到意面。
 * 而其余情况下，3个哲学家中都能有2人吃到意面。
 * 即：33 个人中，22 个人各自持有 22 个叉子，11 个人持有 11 个叉子，共计 55 个叉子。
 *
 * 并且仔细想想，叉子的数目是固定的(个数为5)，直觉上来讲3个人去抢5个叉子 比 4个人去抢5个叉子效率高。
 *
 * 用Semaphore去实现上述的限制：Semaphore eatLimit = new Semaphore(4);
 * 一共有5个叉子，视为5个ReentrantLock，并将它们全放入1个数组中。
 *
 * 给叉子编号 0, 1, 2, 3, 40,1,2,3,4（对应数组下标）。
 * 具体编号我是如下图这般设计的：
 *
 *
 *
 * 有了这些思路，代码实现就变得清晰起来。
 */
public class DiningPhilosophers {

    //1个Fork视为1个ReentrantLock，5个叉子即5个ReentrantLock，将其都放入数组中
    private final ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};

    //限制 最多只有4个哲学家去持有叉子
    private Semaphore eatLimit = new Semaphore(4);
    private int n = 0;

    public DiningPhilosophers(int n) {
        this.n = n;
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = (philosopher + 1) % 5;    //左边的叉子 的编号
        int rightFork = philosopher;    //右边的叉子 的编号

        eatLimit.acquire();    //限制的人数 -1

        lockList[leftFork].lock();    //拿起左边的叉子
        lockList[rightFork].lock();    //拿起右边的叉子

        //pickLeftFork.run();    //拿起左边的叉子 的具体执行
        System.out.println("哲学家"+philosopher+" 拿起了左边的叉子");
        //pickRightFork.run();    //拿起右边的叉子 的具体执行
        System.out.println("哲学家"+philosopher+" 拿起了右边的叉子");

        //eat.run();    //吃意大利面 的具体执行
        System.out.println("哲学家"+philosopher+" 吃面");

        //putLeftFork.run();    //放下左边的叉子 的具体执行
        System.out.println("哲学家"+philosopher+" 放下了左边的叉子");
        //putRightFork.run();    //放下右边的叉子 的具体执行
        System.out.println("哲学家"+philosopher+" 放下了右边的叉子");

        lockList[leftFork].unlock();    //放下左边的叉子
        lockList[rightFork].unlock();    //放下右边的叉子

        eatLimit.release();//限制的人数 +1

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 4; i++) {
            long startTime = System.currentTimeMillis();
            test(i);
            long endTime = System.currentTimeMillis();
            System.out.println(i + " : " + (endTime - startTime));
        }
    }

    private static void test(int n) throws InterruptedException {
        DiningPhilosophers dp = new DiningPhilosophers(n);
        Runnable wait = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        dp.wantsToEat(n, wait, wait, wait, wait, wait);
    }
}
