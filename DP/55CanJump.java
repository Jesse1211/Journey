package DP;

import java.util.Arrays;

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == 0) {
                break;
            }
            for (int j = 1; j < nums[i] + 1; j++) {
                if (i + j < dp.length) {
                    dp[i + j] = 1;
                }
            }
            if (dp[nums.length - 1] == 1) {
                return true;
            }
        }
        return dp[nums.length - 1] == 1;
    }
}