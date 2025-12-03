package DP;

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(dp[j], curMax);
                }
            }
            dp[i] = curMax + 1;
            res = Math.max(dp[i], res);
        }
        return res;
    }
}