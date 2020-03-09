package justin.algorithm.odd_even;

/**
 * 快速高效的判断是否是奇偶数
 */
public class OddEvenNumber {
    public static void main(String[] args) {

        int i = 2;

        System.out.println(i+"是奇数: "+ isOdd(i));
    }

    public static boolean isOdd(int i){
        return  (i & 1) == 1;   //利用与运算
    }

}
