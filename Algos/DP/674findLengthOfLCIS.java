package DP;

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cur += 1;
                res = Math.max(res, cur);
            } else {
                cur = 1;
            }
        }
        return res;
    }
}