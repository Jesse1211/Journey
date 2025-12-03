package PrefixSum;

class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] minFromLeft = new int[n];
        int[] minFromRight = new int[n];

        minFromLeft[0] = nums[0];
        minFromRight[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            minFromLeft[i] = Math.min(minFromLeft[i - 1], nums[i]);
            minFromRight[n - i - 1] = Math.min(minFromRight[n - i], nums[n - i - 1]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int left = minFromLeft[i - 1];
            int cur = nums[i];
            int right = minFromRight[i + 1];
            if (cur > left && cur > right) {
                res = Math.min(res, minFromLeft[i - 1] + nums[i] + minFromRight[i + 1]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;

    }
}