package Array.TwoPointer;

/*
 * highlight: Prefix Sum & Sliding Window
 */

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }

        int left = 0;
        int right = 0;
        int res = Integer.MAX_VALUE;
        while (right <= nums.length) {

            if (sums[right] - sums[left] < target) {
                right++;
            } else {
                res = Math.min(res, right - left);
                left++;
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}