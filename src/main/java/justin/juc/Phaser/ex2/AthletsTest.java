package justin.juc.Phaser.ex2;

import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public class AthletsTest {

    public static void main(String[] args) {
        final Phaser p = new Phaser(6);

        for(int i=1; i<6; i++){
            new Athletes(i, p).start();
        }

        new InjuredAthletes(6, p).start();
    }
}
