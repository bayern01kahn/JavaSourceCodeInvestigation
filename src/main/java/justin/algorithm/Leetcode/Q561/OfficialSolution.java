package justin.algorithm.Leetcode.Q561;

import java.util.Arrays;

public class OfficialSolution {
    public static void main(String[] args) {
        int[] t ={6,2,6,5,1,2};
        System.out.println(arrayPairSum(t));
    }

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
