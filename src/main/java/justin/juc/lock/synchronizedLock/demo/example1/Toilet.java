package justin.juc.lock.synchronizedLock.demo.example1;

import java.util.Random;

// 厕所类
public class Toilet {
    // 厕所的锁
    private final Object lock = new Object();
    private boolean available;

    public Object getLock() {
        return lock;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return available;
    }

    // 场景入口
    public static void main(String[] args) throws Exception {
        Toilet toilet = new Toilet();
        Random random = new Random();
        Thread toiletRepairer = new Thread(new ToiletRepairer(toilet), "ToiletRepairer");
        Thread thread1 = new Thread(new ToiletTask(toilet, "张三", random), "thread-1");
        Thread thread2 = new Thread(new ToiletTask(toilet, "李四", random), "thread-2");
        Thread thread3 = new Thread(new ToiletTask(toilet, "王五", random), "thread-3");
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(100);
        toiletRepairer.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
