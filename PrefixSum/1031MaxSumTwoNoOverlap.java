package PrefixSum;

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] sum = new int[nums.length + 1];

        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int res = 0;
        int max = 0;

        // 1: firstLen is before secondLen
        for (int i = firstLen; i <= nums.length - secondLen; i++) {
            max = Math.max(max, sum[i] - sum[i - firstLen]);
            res = Math.max(res, max + sum[i + secondLen] - sum[i]);
        }

        // 2: vise-versa
        max = 0;
        for (int i = secondLen; i <= nums.length - firstLen; i++) {
            max = Math.max(max, sum[i] - sum[i - secondLen]);
            res = Math.max(res, max + sum[i + firstLen] - sum[i]);
        }
        return res;
    }
}