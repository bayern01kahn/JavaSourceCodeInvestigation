package justin.juc.semaphore;

import java.util.concurrent.Semaphore;


public class SemaphoreDemo {

    //限流（AQS）

    //permits; 令牌(5)

    //公平和非公平

    static class Car extends  Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        public void run(){
            try {
                Thread.currentThread().setName("司机"+num);
                String tName = Thread.currentThread().getName();


                semaphore.acquire(); //获得一个令牌, 如果拿不到令牌，就会阻塞
                System.out.println(tName + " 抢占一个车位, 观察==> 可用车位数: " + semaphore.availablePermits()+ "\t 等待停车数: "+ semaphore.getQueueLength());
                Thread.sleep(2000);

                semaphore.release();
                System.out.println(tName + " 离开一个车位, 观察==> 可用车位数: " + semaphore.availablePermits()+ "\t 等待停车数: "+ semaphore.getQueueLength());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //Semaphore semaphore=new Semaphore(5);
        Semaphore semaphore=new Semaphore(5, true);
        for(int i=1;i<=10;i++){
            new Car(i,semaphore).start();
        }


    }



}
