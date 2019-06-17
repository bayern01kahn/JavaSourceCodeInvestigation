package justin.juc.Phaser.ex1;

import java.util.concurrent.Phaser;


/**
 * Phaser是java7中引入的,属于线程同步辅助工具类.有一类场景:
 * 例如比赛,一个比赛分为3个阶段(phase): 初赛、复赛和决赛,现在规定只要所有运动员完成上一个阶段的比赛就可以进行下一阶段的比赛,
 * 并且比赛的过程中允许退赛(deregister),这个场景就很适合Phaser.
 * <p>
 * 自定义Phaser类,需要重写onAdvance方法
 */
public class MyPhaser extends Phaser {

    //定义结束阶段.这里是完成3个阶段以后结束
    private int phaseToTerminate = 2;

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {

        System.out.println("<第" + phase + "阶段完成>");
        //到达结束阶段,或者还没到结束阶段但是party为0,都返回true,结束phaser
        return phase == phaseToTerminate || registeredParties == 0;
    }

}