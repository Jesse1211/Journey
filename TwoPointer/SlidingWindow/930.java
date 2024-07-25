package TwoPointer.SlidingWindow;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0;
        int sum = 0;
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > goal) {
                sum -= nums[slow++];
            }

            int curSlow = slow;
            int curSum = sum;
            while (curSum == goal && curSlow <= i) {
                res++;
                curSum -= nums[curSlow++];
            }
        }
        return res;
    }
}