package justin.threadlocal;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class threadLocalSourceTest {

    private static final int HASH_INCREMENT = 0x61c88647;


    public static void main(String[] args) {

//        TestHashCodeChange();
//        goldSectionNumber ();
//        testWholeFlow();

        test_idx();

    }

    public static void testWholeFlow() {
        ThreadLocal t1 = new ThreadLocal();

        t1.set(1);
        log.info(t1.get());
        t1.remove();
        log.info(t1.get());
    }

    /**
     * debug 查看  这几个Threadlocal的 threadLocalHashCode值
     */
    public static void TestHashCodeChange() {
        //t1中的threadLocalHashCode变量为0x61c88647
        ThreadLocal t1 = new ThreadLocal();

        //t2中的threadLocalHashCode变量为0x61c88647 + 0x61c88647
        ThreadLocal t2 = new ThreadLocal();

        //t3中的threadLocalHashCode变量为0x61c88647 + 0x61c88647 + 0x61c88647
        ThreadLocal t3 = new ThreadLocal();
    }

    public static void goldSectionNumber () {
        //黄金分割数 * 2的32次方 = 2654435769 - 这个是无符号32位整数的黄金分割数对应的那个值
        long c = (long) ((1L << 32) * (Math.sqrt(5) - 1) / 2);
        System.out.println("无符号黄金分割数: "+ c);
        //强制转换为带符号为的32位整型，值为-1640531527
        int i = (int) c;
        System.out.println("带符号黄金分割数: "+ i);
    }

    //测试斐波拉契散列与普通散列的区别
    //斐波那契散列的非常均匀，普通散列到15个以后已经开发生产碰撞。
    //这也就是斐波那契散列的魅力，减少碰撞也就可以让数据存储的更加分散，获取数据的时间复杂度基本保持在O(1)。
    public static void test_idx() {
        int hashCode = 0;
        for (int i = 0; i < 16; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = hashCode & 15;
            System.out.println("斐波那契散列：" + idx + " 普通散列：" + (String.valueOf(i).hashCode() & 15));
        }
    }
}
