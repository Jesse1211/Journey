package DP;

class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        if (nums.length <= 2) {
            return Math.max(nums[0], nums[1]);
        }

        int res = 0;
        int prev = nums[0]; // 一轮前的最佳结果
        int cur = Math.max(nums[0], nums[1]);// 上轮的最佳结果
        for (int i = 2; i < nums.length; i++) {
            res = Math.max(prev + nums[i], cur); // 一轮前+现在 ?? 上轮 -> 这轮最佳结果
            // shift down
            prev = cur; // 上轮 -- 一轮前
            cur = res; // 这轮 -- 上轮
        }
        return res;
    }
}