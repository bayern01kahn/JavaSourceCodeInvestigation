package justin.algorithm.Leetcode.Q485;

public class Solution {
    public static void main(String[] args) {
        int[] t ={0,1,1,1,1,0,1,0,1,0,1};
        System.out.println(findMaxConsecutiveOnes(t));
    }

    public static  int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int firstZeroLocation = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i == 0) {
                    count = 0;
                    firstZeroLocation = 0;
                }
                if (firstZeroLocation == -1) {
                    count = i;
                }

                if (i - firstZeroLocation > count) {
                    count = i - firstZeroLocation - 1;
                }
                firstZeroLocation = i;
            }
        }
        if (firstZeroLocation == -1) {
            count = nums.length;
        }
        int last1scount = nums.length - firstZeroLocation - 1;
        if (count < last1scount) {
            count = last1scount;
        }
        return count;
    }
}




