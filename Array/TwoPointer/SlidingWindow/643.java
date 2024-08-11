package TwoPointer.SlidingWindow;

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < k; i++) {
            res += nums[i];
        }

        if (nums.length < k) {
            return res / nums.length;
        }

        int slow = 0;
        int cur = res;
        for (int i = k; i < nums.length; i++) {
            cur -= nums[slow++];
            cur += nums[i];
            res = Math.max(res, cur);
        }
        return (double) res / k;
    }
}