package justin.juc.Phaser.ex2;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class InjuredAthletes extends Thread {

    private final int no;
    private final Phaser phaser;

    private final Random random = new Random(System.currentTimeMillis());

    public InjuredAthletes(int no, Phaser phaser) {
        this.no = no;
        this.phaser = phaser;
    }

    public void run(){
        try{
            sport(" Start running", " End running");
            phaser.arriveAndAwaitAdvance();

            sport(" Start bicycle", " End bicycle");
            phaser.arriveAndAwaitAdvance();

            System.out.println("No:"+no+" injured !!!!!!!!!!. i have to exit.");
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sport(String s, String s2) throws InterruptedException {
        System.out.println("No:" + no + s);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println("No:" + no + s2);
    }
}
