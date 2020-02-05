package justin.guava.preconditions;

import com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.IntStream;

public class T1 {

    public static void main(String[] args) {
        List<Integer> intList = Lists.newArrayList();


        IntStream.range(0,10).forEach((i)-> {
            try {
                //System.out.println("i:" + i);
                intList.add(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });//从0-9
        System.out.println("intList Size: "+ intList.size());

        //表达式为true不抛异常
        //Preconditions.checkState(intList.size() < 10, " intList size 不能大于" + 10);
        Preconditions.checkPositionIndex(9, intList.size(), "index " + 9 + " 不在 list中， List size为：" + intList.size());
        Preconditions.checkElementIndex(11, intList.size(), "index 为 " + 11 + " 不在 list中， List size为： " + intList.size());


//        insert("", -1);
//        insert("123", -1);
        insert("Justin", 88);
        System.out.println("顺利执行");
    }

    public static void insert(String name, int age) {
        //Preconditions.checkNotNull(name);
        //Preconditions.checkArgument(age >= 0);
        Preconditions.checkNotNull(name, "name为null");
        //Preconditions.checkArgument(!name.equals(""), "Name = \"\"");
        Preconditions.checkArgument(name.length() > 0, "name为\'\'");
        Preconditions.checkArgument(age > 0, "age 必须大于0");
    }


    public static void checkPositionIndex(List<Integer> intList, int index) throws Exception {

    }

    public static void checkPositionIndexes(List<Integer> intList, int start, int end) throws Exception {
        Preconditions.checkPositionIndexes(start, end, intList.size());
    }

    public static void checkElementIndex(List<Integer> intList, int index) throws Exception {

    }
}
