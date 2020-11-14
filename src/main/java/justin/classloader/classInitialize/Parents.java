package justin.classloader.classInitialize;

public class Parents {
    static {
        System.out.println("Parent static invoke");
    }
    public static final String FINAL_STR="FINAL_STR";
    public static final Test FINAL_OBJECT=new Test();
    public Parents(){
        System.out.println("Parent init");
    }
}

class Children extends Parents {
    static {
        System.out.println("Child static invoke");
    }
    public Children(){
        System.out.println("child init");
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(Children.FINAL_STR);
        /**
         * 调用 Children.FINAL_STR, FINAL_STR 是 Parents的 static final 变量 那么不会触发 类的静态语句和静态变量的初始化操作
         * 结果: FINAL_STR
         */
    }
}

class Test2 {
    public static void main(String[] args) {
        System.out.println(Children.FINAL_OBJECT);
        /**
         * 调用 Children.FINAL_OBJECT, FINAL_OBJECT 是 Parents的 static变量. 由于并不是常量池中的对象,所以会触发 类的静态语句和静态变量的初始化操作
         * 结果:
         * Parent static invoke
         * justin.classloader.classInitialize.Test@45ee12a7
         */
    }
}