package justin.juc.Unsafe.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 2个线程 交替 打印  123456789 和  ABCDEFGHI
 */
public class Demo1 {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {

            for(char c : aI){
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {

            for(char c : aC){
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
