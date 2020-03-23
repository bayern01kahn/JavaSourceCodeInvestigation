package justin.algorithm.question;

import com.google.common.math.LongMath;
import org.junit.Test;

/**
 * 求2个数的最大公约数
 *
 * 1.暴力遍历
 * 2.辗转相除法
 * 3.更相相减法
 * 4.更相相减+位移运算
 * 5.使用Guava 自带函数
 */
public class GreatestCommonDivisor {

    @Test
    public void work(){

        //way 1 遍历全部
        System.out.println(getGreatestCommonDivisor(25, 5));
        System.out.println(getGreatestCommonDivisor(100, 80));

        //way 2 辗转相除法
        System.out.println(getGreatestCommonDivisor2(25, 5));
        System.out.println(getGreatestCommonDivisor2(100, 80));

        //way 3 更相减损数
        System.out.println(getGreatestCommonDivisor3(25, 5));
        System.out.println(getGreatestCommonDivisor3(100, 80));

        //way 4 更相减损数 与 位移运算结合
        System.out.println(gcd(25, 5));
        System.out.println(gcd(100, 80));
        System.out.println(gcd(27, 14));
        System.out.println(gcd(10000, 80));

        //way 5 Guava
        System.out.println(LongMath.gcd(25, 5));
        System.out.println(LongMath.gcd(100, 80));
    }

    private int gcd(int a , int b){
        if (a==b)
            return a;
        if((a&1)==0 && (b&1)==0){                //a b 同时为偶数
            return gcd(a>>1, b>>1)<<1;
        } else if((a&1)==0 && (b&1)!=0){         //a是偶数  b是奇数
            return gcd(a>>1, b);
        } else if ((a&1)!=0 && (b&1)==0){        //a是奇数  b是偶数
            return  gcd(a, b>>1);
        } else {                                 //同时是奇数时, 先更相相减, 那么 a-b 必然是偶数 又可以继续进行位移运算
            int big = a>b ? a:b;
            int small = a<b ? a:b;
            return gcd(big-small, small);
        }
    }

    private int getGreatestCommonDivisor2(int a, int b) {
        int big = a>b ? a:b;
        int small = a<b ? a:b;
        if(big%small==0){
            return small;
        }
        return getGreatestCommonDivisor2(big%small, small);
    }

    private int getGreatestCommonDivisor3(int a, int b) {
        int big = a>b ? a:b;
        int small = a<b ? a:b;
        if(big%small==0){
            return small;
        }
        return getGreatestCommonDivisor3(big-small, small);
    }

    private int getGreatestCommonDivisor(int a, int b) {
        int big = a>b ? a:b;
        int small = a<b ? a:b;

        if(big%small==0){
            return small;
        }

        for(int i=small/2; i>1; i--){
            if(small%i==0 && big%i==0){
                return i;
            }
        }
        return 1;
    }
}
