package justin.test;

public interface
Java8Test {
    void dosth();

    //8 的 接口可以申明实体方法,用来扩展名多继承
    default void TODO(){
        System.out.println("想不到吧");
    }
}
