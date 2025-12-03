package DP;

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int noHead = helper(nums, 0, nums.length - 1);
        int noTail = helper(nums, 1, nums.length);
        return Math.max(noHead, noTail);
    }

    public int helper(int[] nums, int start, int end) {
        if (nums.length <= start + 1) {
            return nums[start];
        }
        int prev = nums[start];
        int cur = Math.max(nums[start], nums[start + 1]);
        int res = cur;
        for (int i = start + 2; i < end; i++) {
            res = Math.max(cur, prev + nums[i]);
            prev = cur;
            cur = res;
        }
        return res;
    }
}