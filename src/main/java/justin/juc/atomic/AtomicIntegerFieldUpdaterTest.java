package justin.juc.atomic;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.IntStream;


/**
 *   AtomicXXXFieldUpdater
 *   用于 对某个类 中的某个属性 进行原子性保证, 不用强行使用 AtomicReference 来把整个类都加入atomic
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        final int Max = 5;
        final int ThreadNumber = 200;
        TestMe me= new TestMe();
        me.setX(0);
        me.setY(0);

        /**
         * 未使用 AtomicXXXFieldUpdater,  最终结果就不会是1000
         */
        for(int i=0;i<ThreadNumber;i++){
            new Thread(() -> {
                for (int j = 0; j < Max; j++) {
                    me.setX(me.getX()+1);
                    System.out.println(Thread.currentThread().getName() + "=>" + me.getX());
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println("\n\n\n\n\n\n");

        /**
         * stream 方式 同时使用 AtomicXXXFieldUpdater, 可以保证 i 的原子操作, 最终结果是1000
         */
        IntStream.range(0,ThreadNumber).forEach((i) -> {
            new Thread(() -> {
                IntStream.range(0, Max).forEach((j) -> {
                    int v = updater.getAndIncrement(me);
                    System.out.println(Thread.currentThread().getName() + "=>" + v);
                });
            }).start();
        });

    }

    static class TestMe {
        volatile int i =1;
        volatile int x =1;
        volatile int y =1;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
