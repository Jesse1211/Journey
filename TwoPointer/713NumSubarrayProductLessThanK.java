package TwoPointer;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;

        int res = 0;
        int cur = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            cur *= nums[right];

            if (cur < k) {
                res += right - left + 1;
            } else {
                while (cur >= k) {
                    cur = cur / nums[left];
                    left++;
                }
                res += right - left + 1;
            }
            right++;
        }

        return res;
    }
}