package justin.innerClass.Demo4;

public class MyDemo {
    public void test() {
        System.out.println("父类的test方法");
    }
}

interface Demo {
    void test();
}

/**
 * 这样如何区分这个方法是接口的还是继承的，所以我们使用内部类解决这个问题
 */
class DemoTest extends MyDemo implements Demo {
    public void test() {
    }
}

/**
 * 所以使用内部匿名类来解决这个问题
 */
class DemoTest2 extends MyDemo {

    private class inner implements Demo {
        public void test() {
            System.out.println("接口的test方法");
        }
    }

    public Demo getIn() {
        return new inner();
    }


    public static void main(String[] args) {
        //调用接口而来的test()方法
        DemoTest2 dt = new DemoTest2();
        Demo d = dt.getIn();
        d.test();

        //调用继承而来的test()方法
        dt.test();
    }
}
