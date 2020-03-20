package justin.designPattern.singleton.doublecheck;

public class DCSingleton {

    private static volatile DCSingleton instance;

    public DCSingleton() {
    }

    public static DCSingleton getInstance(){
        if(null == instance){
            synchronized (DCSingleton.class){
                if(null == instance){
                    instance = new DCSingleton();
                }
            }
        }
        return DCSingleton.instance;
    }
}
