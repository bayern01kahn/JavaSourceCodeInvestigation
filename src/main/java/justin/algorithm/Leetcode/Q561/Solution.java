package justin.algorithm.Leetcode.Q561;

/**
 * 最优解 桶排序+贪心算法
 */
public class Solution {
    public static void main(String[] args) {
        int[] t ={6,2,6,5,1,2};
        System.out.println(arrayPairSum(t));
    }

    public static int arrayPairSum(int[] nums) {
        /**
         * 桶排序
         */
        int[] arr = new int[20001];
        for (int i : nums) {
            arr[i + 10000]++;
        }

        boolean pick = true;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            int val = i - 10000;
            /**
             * 贪心算法
             */
            while (arr[i] > 0) {
                if (pick)
                    sum += val;
                pick = !pick;
                arr[i]--;
            }
        }
        return sum;
    }
}




