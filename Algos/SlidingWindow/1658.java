package SlidingWindow;

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = -1 * x;
        for (int num : nums) {
            n += num;
        }

        if (n < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];

            while (sum > n) {
                sum -= nums[left];
                left++;
            }

            if (sum == n) {
                res = Math.min(res, nums.length - (right - left + 1));
            }

            right++;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}