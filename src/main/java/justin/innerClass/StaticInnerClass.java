package justin.innerClass;

/**
 * 静态内部类
 * 我们所知道static是不能用来修饰类的,但是成员内部类可以看做外部类中的一个成员,所以可以用static修饰,
 * 这种用static修饰的内部类我们称作静态内部类,也称作嵌套内部类.
 *
 * 特点：不能使用外部类的非static成员变量和成员方法
 */
public class StaticInnerClass {
    int age = 10;
    static int age2 = 20;
    public StaticInnerClass() {
    }

    static class Inner {
        public void method() {
            //System.out.println(age);//错误
            System.out.println(age2);//正确
        }
    }

}
