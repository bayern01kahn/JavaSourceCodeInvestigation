package justin.classloader.classInitialize;

/**
 * 主动使用与被动使用 对类初始化的影响
 */

public class ActiveUseTest {

    public static void main(String[] args) throws ClassNotFoundException {

        // 主动使用1.new,直接使用
        Obj obj = new Obj();

        // 主动使用2.访问某个类或者接口的静态变量,或者对静态变量进行赋值操作
        System.out.println(Obj.i);
        Obj.i = 2;
        System.out.println(I.a);

        // 主动使用3.调用静态方法
        Obj.call();

        // 主动使用4.反射某个类
        System.out.println(Class.forName("justin.classloader.classInitialize.Obj"));


        // 主动使用5.初始化一个子类, 其父类也会被初始化
        System.out.println(Child.j);


        // 主动使用6.启动类, 比如 java HelloWorld



        //  特殊例子
        //  被动使用.1  使用子类调用父类的静态变量  因为 静态变量其实不属于某个类, 而存在于静态变量表中
        System.out.println(Child.i);  //此时只会初始化父类, 而子类不会被初始化

        //  被动使用.2  数组,  此时 也不会初始化 父类
        Obj[] arrays = new Obj[10];

        //  被动使用.3  引用常量,  此时 也不会初始化 父类
        System.out.println(Obj.x);
    }
}

class Obj{

    public static final int x= 10;

    public static int i = 1;

    public static void call(){

    }

    static {
        System.out.println("Obj 完成初始化 x = "+x+" i = "+i);
    }

}

class Child extends  Obj{

    public static int j = 3;

    static {
        System.out.println("Child 完成初始化 j="+j);
    }


    public Child() {

    }
}


interface  I{
    int a= 10;
}