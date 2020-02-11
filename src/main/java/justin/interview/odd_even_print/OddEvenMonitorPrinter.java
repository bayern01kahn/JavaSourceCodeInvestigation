package justin.interview.odd_even_print;

public class OddEvenMonitorPrinter {

    private final Object monitor = new Object();
    private final int limit;
    private volatile int count;

    public OddEvenMonitorPrinter(int limit, int initCount) {
        this.limit = limit;
        this.count = initCount;
    }

    public void print() {
        synchronized (monitor) {
            while (count < limit) {
                try {
                    System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), ++count));
                    monitor.notifyAll();
                    monitor.wait();
                } catch (InterruptedException e) {
                    //ignore
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        OddEvenMonitorPrinter printer = new OddEvenMonitorPrinter(10, 0);
        Thread thread1 = new Thread(printer::print, "thread-A");
        Thread thread2 = new Thread(printer::print, "thread-B");
        thread1.start();
        thread2.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
