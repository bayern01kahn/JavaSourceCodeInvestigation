package justin.designPattern.singleton.threadLocalSingleton;


public class ExectorThread implements Runnable{
    @Override
    public void run() {
        ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}
