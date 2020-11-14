package justin.innerClass;

/**
 * 成员内部类 - 就是位于外部类成员位置的类 最常见的内部类
 * 特点：可以使用外部类中所有的成员变量和成员方法（包括private的）
 */
public class MemberInnerClass {
    private int age = 20;
    //成员位置
    class Inner {
        public int age = 20;

        public void show() {
            int age = 25;
            System.out.println(age);//空1
            System.out.println(this.age);//空2
            System.out.println(MemberInnerClass.this.age);//空3
        }
    }
}

class Test {

    public static void main(String[] ages) {
        //成员内部类是非静态的演示
        MemberInnerClass.Inner oi = new MemberInnerClass().new Inner();
        oi.show();
    }
}

