package justin.designPattern.singleton.enumSingleton_recommend;

import java.lang.reflect.Constructor;

public class EnumSingletonTest {

    public static void main(String[] args) {

        //正常使用
         Object obj = new Object();
         Object obj1 = new Object();

         EnumSingleton e = EnumSingleton.getInstance();
         e.setData(obj);
         System.out.println(e.getData().toString());
         e = EnumSingleton.getInstance();
         e.setData(obj1);
         System.out.println(e.getData().toString());

        // 演示 枚举单例无法被反射暴力破解
        try {
            Class clazz = EnumSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
            c.setAccessible(true);
            EnumSingleton enumSingleton = (EnumSingleton)c.newInstance("Tom",666);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}