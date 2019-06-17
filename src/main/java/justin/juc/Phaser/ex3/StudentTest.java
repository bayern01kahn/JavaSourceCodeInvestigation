package justin.juc.Phaser.ex3;

public class StudentTest {


    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();
        StudentTask[] studentTask = new StudentTask[5];
        for (int i = 0; i < studentTask.length; i++) {
            studentTask[i] = new StudentTask(phaser);
            phaser.register();  //注册一次表示phaser维护的线程个数
        }

        Thread[] threads = new Thread[studentTask.length];
        for (int i = 0; i < studentTask.length; i++) {
            threads[i] = new Thread(studentTask[i], "Student "+i);
            threads[i].start();
        }

        //等待所有线程执行结束
        for (int i = 0; i < studentTask.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Phaser has finished:"+phaser.isTerminated());

    }
}
