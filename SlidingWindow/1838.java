package SlidingWindow;

import java.util.Arrays;

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int res = -1;
        long sum = 0;

        for (int right = 0; right < nums.length; right++) {
            long cur = nums[right];
            sum += cur;

            while (((right - left + 1) * cur) - sum > k) {
                sum -= (long) nums[left];
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
