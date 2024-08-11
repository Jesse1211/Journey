package TwoPointer;

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