package SlidingWindow;

import java.util.Arrays;

class Solution {
    public int[] getAverages(int[] nums, int k) {
        // nums = [7,4,3,9,1,8,5,2,6]

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        long[] sums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }

        int windowSize = 2 * k + 1;
        // sums:[7,11,14,23,24,32,37,39,45]
        for (int i = k; i < nums.length - k; i++) {
            int left = i - k;
            int right = i + k;

            long sub = sums[right + 1] - sums[left];
            int cur = (int) (sub / windowSize);
            res[i] = cur;
        }
        return res;
    }
}