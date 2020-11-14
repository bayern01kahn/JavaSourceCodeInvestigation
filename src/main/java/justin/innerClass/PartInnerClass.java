package justin.innerClass;

/**
 * 局部内部类——就是定义在一个方法或者一个作用域里面的类
 * 特点：主要是作用域发生了变化，只能在自身所在方法和属性中被使用
 */
public class PartInnerClass {
    private int age = 20;
    public void method() {
        final int age2 = 30;

        class Inner {
            public void show() {
                System.out.println(age);
                //从内部类中访问方法内变量age2，需要将变量声明为最终类型。
                /**
                 * 为什么局部内部类访问局部变量必须加final修饰呢？因为局部变量是随着方法的调用而调用，使用完毕就消失，
                 * 而堆内存的数据并不会立即消失。所以，堆内存还是用该变量，而该变量已经没有了。为了让该值还存在，就加final修饰。
                 * 原因是，当我们使用final修饰变量后，堆内存直接存储的是值，而不是变量名。
                 * （即 age2 的位置存储着常量30 而不是 age2 这个变量名）
                 */
                System.out.println(age2);
            }
        }

        Inner i = new Inner();
        i.show();
    }

}

class Test1 {

    public static void main(String[] ages) {
        //成员内部类是非静态的演示
        PartInnerClass oi = new PartInnerClass();
        oi.method();
    }
}
