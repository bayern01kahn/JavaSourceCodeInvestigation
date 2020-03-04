package justin.integer;

public class T1 {

    /**
     * Integer.valueOf() 源码
     * Integer.IntegerCache.low = -128
     * Integer.IntegerCache.high = 127
     * Integer.IntegerCache.cache[255]  是个 从 -128 => 127 的 数组
     */
//    public static Integer valueOf(int i) {
//        if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
//            return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
//        return new Integer(i);
//    }



    public static void main(String[] args) {


        /** 用于debug valueOf()   [-128 - 127]  **/
//        Integer i = Integer.valueOf(121);
//        System.out.println(i);


        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        System.out.println("i == i2 = " + (i == i2)); // Integer会自动拆箱为int，所以为true
        System.out.println("i == i3 = " + (i == i3)); // true，理由同上

        Integer i4 = 127; // 编译时被翻译成：Integer i4 = Integer.valueOf(127);
        Integer i5 = 127;
        System.out.println("i4 == i5 = " + (i4 == i5)); // true

        Integer i6 = 128;
        Integer i7 = 128;
        System.out.println("i6 == i7 = " + (i6 == i7)); // false  这里因为 Integer cache 范围 [-128,127] 被超出 所以会new 新对象

        Integer i8 = new Integer(127);
        System.out.println("i5 == i8 = " + (i5 == i8)); // false  这里 i5 会使用缓存, i8会新建对象

        Integer i9 = new Integer(128);
        Integer i10 = new Integer(128);
        System.out.println("i9 == i10 = " + (i9 == i10));// false  这里会新建2个不同的对象


        Integer a = new Integer(127), b = new Integer(128);
        int c = 127, d = 128, dd = 128;
        Integer e = 127, ee = 127, f = 128, ff = 128;
        System.out.println(a == b); // false 因为a,b都是new出来的对象，地址不同所以为false
        System.out.println(a == c); // true a会自动拆箱为int类型
        System.out.println(a == e); // false 指向的地址不同a是new出来的
        System.out.println(e == c); // true e会自动拆箱为int类型
        System.out.println(e == ee);// true Integer对 处于-128到127范围之间，指向了同一片地址区域
        System.out.println(b == f); // false 指向的地址不同b是new出来的
        System.out.println(f == d); // true f自动拆箱为int类型
        System.out.println(f == ff);
        /*
         * false 指向的不是同一片地址区域。
         * 在Integer类型中，-128到127存放的是同一片区域地址，
         * 之外的数是另外开辟空间来进行 存储的
         */
        System.out.println(d == dd);// true 不解释



        int t1 = 10000;
        Integer t2 = 10000;
        Integer t3 = new Integer(10000);
        System.out.println(t1 == t2);  //true
        System.out.println(t1 == t3);  //true   Integer会自动拆箱为int
        System.out.println(t2 == t3);  //false

        int z1 = 100;
        Integer z2 = 100;
        Integer z3 = new Integer(100);
        System.out.println(z1 == z2);  //true   Integer会自动拆箱为int
        System.out.println(z1 == z3);  //true   Integer会自动拆箱为int
        System.out.println(z2 == z3);  //false
    }
}
