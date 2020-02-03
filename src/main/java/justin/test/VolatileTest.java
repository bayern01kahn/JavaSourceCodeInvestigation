package justin.test;

public class VolatileTest {

//  int a = 0;                 //1
//  int b = 1;                 //2
    public static volatile int c = 0;        //3
//  int d = 3;                 //4
//  int e = 4;                 //5


    public static void increase(){
        c++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    increase();
                }
            }
            ).start();
        }
        Thread.sleep(5000);
        System.out.println(c);
    }
}

//运行3次结果分别是：997，995，989