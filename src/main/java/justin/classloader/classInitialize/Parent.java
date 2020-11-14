package justin.classloader.classInitialize;

public class Parent {
    public static int A = 2;

    static {
        A = 1;
    }


    public static void main(String[] args) {
        System.out.println(Sub.B);  // 输出结果是父类中的静态变量 A 的值，也就是 2。
    }
}

class Sub extends Parent {
    /**
     * !!!!
     * 静态语句块只能访问到定义在它之前的类变量，
     * 定义在它之后的类变量只能赋值，不能访问
     * !!!!
     */
    static {
        i = 0;                // 给变量赋值可以正常编译通过
        //System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }
    public static int i = 1;

    public static int B = A;
}


