package justin.algorithm.question;

import org.junit.Test;

/**
 * 算法：时间复杂度为O(n)--几乎是完美的算法（需要常量空间并以线性时间运行的联机算法）
 * 联机算法，在任意时刻都能对他已经读入的数据给出子序列问题的正确答案
 */
public class MaxSubsequenceSum {

    @Test
    public void work() {
        int[] arr = {5,75,20,-100,200,3,1};

        System.out.println("最大子序列和: "+ getMaxSubsequenceSum(arr));
    }

    private int getMaxSubsequenceSum(int[] arr) {
        int length = arr.length;
        int temp=0, max=0;

        for(int i=0; i<length; i++){
            temp += arr[i];
            if( temp > max ){
                max = temp;
            }
            if( temp < 0){
                temp = 0;
            }
        }
        return max;
    }
}
