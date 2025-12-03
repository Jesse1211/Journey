package DP;

import java.util.Arrays;

class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums[i] + 1; j++) {
                if (i + j < dp.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}