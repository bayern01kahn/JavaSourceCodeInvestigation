package justin.juc.thread.interrupt;

public class T1{

    /**
     * interrupt()，在一个线程中调用另一个线程的interrupt()方法，即会向那个线程发出信号——线程中断状态已被设置。至于那个线程何去何从，由具体的代码实现决定。
     * isInterrupted()，用来判断当前线程的中断状态(true or false)。
     * 源码:
     * public static boolean interrupted() {
     *   return currentThread().isInterrupted(true);
     * }
     *
     * interrupted()是个 Thread 的 static 方法，测试 当前线程 是否已经是中断状态，执行后具有清除状态功能
     * 源码:
     * public boolean isInterrupted() {
     *   return isInterrupted(false);
     * }
     */


    private volatile boolean on = true;

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        //t1.interruptTest();
        //t1.interruptWithFlagTest();
        //t1.interruptTest2();

        t1.interruptedTest1();
    }


    /**
     * 启动之后未加限制的线程 无法被中断 只能改变中断标志
     */
    public void interruptTest() throws InterruptedException {
        Thread testThread = new Thread(()-> {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Yes,I am interruted,but I am still running");

                }else{
                    System.out.println("not yet interrupted");
                }
            }
        });

        testThread.start();
        Thread.sleep(1000);

        testThread.interrupt();
        System.out.println("main end");
    }



    /**
     * 加入中断标志 每次判断时添加一个开关
     */
    public void interruptWithFlagTest() throws InterruptedException {
        Thread testThread = new Thread(()-> {
            while(on){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Yes,I am interruted,but I am still running");

                }else{
                    System.out.println("not yet interrupted");
                }
            }
        });

        testThread.start();
        Thread.sleep(1);

        on = false;  //中断标识开关
        //testThread.interrupt();

        System.out.println("main end");
    }


    /**
     * 遇到阻塞的线程时, 需要调用 interrupt() 来达到中断的目地
     */
    public void interruptTest2() throws InterruptedException {
        Thread testThread = new Thread(()-> {
            while(on){
                try {
                    System.out.println("开始阻塞");
                    Thread.sleep(10000000);
                    System.out.println("停止阻塞");
                } catch (InterruptedException e) {
                    System.out.println("调用中断 停止阻塞线程: "+e);
                }
            }
        });

        testThread.start();
        Thread.sleep(1000);

        on = false;  //中断标识开关
        testThread.interrupt();

        System.out.println("main end");
    }

    public void interruptedTest1(){
        System.out.println(Thread.interrupted());   //未调用interrupt()之前 是 false
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());   // 此时状态为 true ,但是,调用之后同时会把中断状态重置为 false
        System.out.println(Thread.interrupted());   // 再次调用时, 是重置后的状态  false
        System.out.println(Thread.interrupted());   // 再次调用时, 是重置后的状态  false
    }}



