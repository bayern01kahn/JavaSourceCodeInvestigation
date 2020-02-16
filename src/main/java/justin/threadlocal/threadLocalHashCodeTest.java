package justin.threadlocal;

public class threadLocalHashCodeTest {

    /**
     * debug 查看  这几个Threadlocal的 threadLocalHashCode值
     * @param args
     */
    public static void main(String[] args) {
        //t1中的threadLocalHashCode变量为0x61c88647
        ThreadLocal t1 = new ThreadLocal();

        //t2中的threadLocalHashCode变量为0x61c88647 + 0x61c88647
        ThreadLocal t2 = new ThreadLocal();

        //t3中的threadLocalHashCode变量为0x61c88647 + 0x61c88647 + 0x61c88647
        ThreadLocal t3 = new ThreadLocal();
    }

}
