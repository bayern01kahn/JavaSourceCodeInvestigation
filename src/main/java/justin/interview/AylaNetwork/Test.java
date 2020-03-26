package justin.interview.AylaNetwork;

public class Test {
        public class Test2 extends Test {
            public Test2() {
                if (n == 0x10)
                    n = 10;
                else
                    n = 0_10;
            }
            public int get() { return n; }
        }

        int n = 0x10;
        public Test() { n = 0b10; }
        public static void main(String[] args) {
            final Test2 t2 = new Test().new Test2();
            System.out.println(t2.get());
        }
//    public class Test2 extends Test {
//        public Test2() {
//            System.out.println("Test2构造函数: 此时n为父类的n: "+ n);
//            if (n == 0x10)   //16进制 => 16
//                n = 10;
//            else
//                n = 0_10;    //8进制  => 8
//            System.out.println("Test2构造函数: 此时n为 0_10: "+ n);
//        }
//        public int get() { return n; }
//    }
//
//    int n = 0x10;
//    public Test() {
//        n = 0b10;
//        System.out.println("Test构造函数: 二进制0b10: "+ n);
//    }
//
//    public static void main(String[] args) {
//            final Test2 t2 = new Test().new Test2();
//            System.out.println(t2.get());
//    }

    @org.junit.Test
    public void test2_2(){
        int x = 5;
        boolean b1 = true;
        boolean b2 = false;
        if ((x == 4) && !b2)
            System.out.print("1 ");
        System.out.print("2 ");
        if ((b2 = true) && b1)
            System.out.print("3 ");
    }


    @org.junit.Test
    public void test2_3(){
        try {
            getsth();
            return;
        } catch (Exception ex) {
            System.out.println("Catch");
        } finally {
            System.out.println("Finally");
        }
        System.out.println("Exit");
    }

    public static void getsth() {
        System.out.println("Get");
    }
}
