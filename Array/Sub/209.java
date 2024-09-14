package Sub;

/*
 * highlight: Prefix Sum & Sliding Window
 */

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int cur = 0;
        while (right < nums.length) {
            cur += nums[right];

            while (cur >= target) {
                res = Math.min(res, right - left + 1);
                cur -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

class Solution1 {
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