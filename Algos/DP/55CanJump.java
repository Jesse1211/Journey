package DP;

import java.util.Arrays;

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        if (nums[0] == 0) {
            return false;
        }

        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (dp[i] == false) {
                return false;
            }

            int dis = nums[i];
            int leftMost = i - dis; // inclusive
            if (leftMost < 0) {
                leftMost = 0;
            }
            int rightMost = i + dis; // inclusive
            if (rightMost >= nums.length) {
                rightMost = nums.length - 1;
            }

            while (leftMost <= rightMost) {
                dp[leftMost] = true;
                leftMost++;
            }
        }

        return dp[nums.length - 1];
    }
}