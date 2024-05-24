package DP;

class Solution {
    public int maxSubArray(int[] nums) {
        int cur = nums[0];
        int res = cur;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i] + cur) {
                cur = nums[i];
            } else {
                cur += nums[i];
            }
            res = Math.max(cur, res);
        }
        return res;
    }
}