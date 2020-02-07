package justin.juc.completableFuture.multipleAPI;

import java.util.Random;

public interface Api{

    int getValue();

    int getSecondValue();


    //Java 8 接口 可以申明实体方法,用来扩展名多继承
    default int handle(String name)  {

        int min =1;
        int max =5;
        Random random = new Random();
        int time = random.nextInt(max)%(max-min+1) + min;

        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("API : "+name+ " Cost: "+ time +"s");
        return time;
    }
}
