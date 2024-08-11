package TwoPointer.SlidingWindow;

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0;
        int prod = 1;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            prod *= nums[fast];

            while (prod >= k) {
                prod /= nums[slow];
                slow++;
            }

            res += fast - slow + 1;

        }
        return res;
    }
}