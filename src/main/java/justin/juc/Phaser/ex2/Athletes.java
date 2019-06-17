package justin.juc.Phaser.ex2;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Athletes extends Thread {

    private final int no;
    private final Phaser phaser;

    private final Random random = new Random(System.currentTimeMillis());

    public Athletes(int no, Phaser phaser) {
        this.no = no;
        this.phaser = phaser;
    }

    public void run(){
        try{
            sport(" Start running", " End running");
            phaser.arriveAndAwaitAdvance();

            sport(" Start bicycle", " End bicycle");
            phaser.arriveAndAwaitAdvance();

            sport(" Start swimming", " End swimming");
            phaser.arriveAndAwaitAdvance();

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
