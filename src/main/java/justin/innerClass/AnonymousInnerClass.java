package justin.innerClass;

/**
 * 一个没有名字的类，是内部类的简化写法
 *
 * 本质：其实是继承该类或者实现接口的子类匿名对象
 *
 * 匿名内部类在开发中的使用
 *
 * ​我们在开发的时候，会看到抽象类，或者接口作为参数。
 * 而这个时候，实际需要的是一个子类对象。
 * 如果该方法仅仅调用一次，我们就可以使用匿名内部类的格式简化。
 */
public class AnonymousInnerClass {


    public void method(){

        //Lambda 方式
        //((Inner) () -> System.out.println("Hello Anonymous Inner Class")).show();

        //常规 方式
        new Inner() {

            @Override
            public void demoMethod() {
                System.out.println("method + 具体实现接口");
            }
        };
    }

    public void test(Inner inner){
        System.out.println("test method");
    }


    public static void main(String[] args)  {

        AnonymousInnerClass md = new AnonymousInnerClass();

        //这里我们使用匿名内部类的方式将接口对象作为参数传递到test方法中去了
        md.test(new Inner() {
            @Override
            public void demoMethod(){
                System.out.println("具体实现接口");
            }
        });

    }
}

interface Inner {
    void demoMethod();
}

