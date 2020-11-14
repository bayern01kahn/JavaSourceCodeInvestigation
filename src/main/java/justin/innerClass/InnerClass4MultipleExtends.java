package justin.innerClass;

/**
 * 我们编写了两个待继承的类Demo1和Demo2，在InnerClass4MultipleExtends类中书写了两个内部类，
 * test1和test2 两者分别继承了Demo1和Demo2类，这样MyDemo中就间接的实现了多继承
 */
public class InnerClass4MultipleExtends {

    private class test1 extends Demo1 {
        public String name() {
            return super.name();
        }
    }

    private class test2 extends Demo2 {
        public String email() {
            return super.email();
        }
    }

    public String name() {
        return new test1().name();
    }

    public String email() {
        return new test2().email();
    }

    public static void main(String args[]) {
        InnerClass4MultipleExtends md = new InnerClass4MultipleExtends();
        System.out.println("我的姓名:" + md.name());
        System.out.println("我的邮箱:" + md.email());
    }
}

class Demo1 {
    public String name() {
        return "jusitn";
    }
}

class Demo2 {
    public String email() {
        return "xx.@xx.com";
    }
}
