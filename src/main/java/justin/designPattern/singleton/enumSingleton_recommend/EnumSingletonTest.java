package justin.designPattern.singleton.enumSingleton_recommend;

import java.lang.reflect.Constructor;

public class EnumSingletonTest {

    public static void main(String[] args) {
        try {
            Class clazz = EnumSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
            c.setAccessible(true);
            EnumSingleton enumSingleton = (EnumSingleton)c.newInstance("Tom",666);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}