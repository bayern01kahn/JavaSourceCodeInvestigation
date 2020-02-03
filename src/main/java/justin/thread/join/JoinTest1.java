package justin.thread.join;


public class JoinTest1 {


    /**
     *  用于帮助理解join方法的作用,以及调用某个线程的join后, 调用者的线程的状态追踪
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread mainThread = Thread.currentThread();

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("In     Join => "+"T1  : "+ Thread.currentThread().getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
                System.out.println("In     Join => "+"Main: "+ mainThread.getState().name()+"\t"+ " Time cost: " + (System.currentTimeMillis() - start));
                Thread.sleep(1000);
                System.out.println("In     Join => "+"T1  : "+ Thread.currentThread().getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
                System.out.println("In     Join => "+"Main: "+ mainThread.getState().name()+"\t"+ "Time cost: " + (System.currentTimeMillis() - start));
                Thread.sleep(2000);
                System.out.println("In     Join => "+"T1  : "+ Thread.currentThread().getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
                System.out.println("In     Join => "+"Main: "+ mainThread.getState().name()+"\t"+ "Time cost: " + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("T1  : "+ t1.getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
        t1.start();

        try {
            System.out.println("Before Join => "+"T1  : "+ t1.getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
            System.out.println("Before Join => "+"Main: "+ Thread.currentThread().getState().name()+"\t"+ "Time cost: " + (System.currentTimeMillis() - start));
            t1.join();
            System.out.println("After  Join => "+"T1  : "+ t1.getState().name()+ "\t"+ "Time cost: " + (System.currentTimeMillis() - start));
            System.out.println("After  Join => "+"Main: "+ Thread.currentThread().getState().name()+"\t"+ "Time cost: " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            System.out.println("等待中断异常");
            e.printStackTrace();
        }
    }
}
