package TwoPointer.SlidingWindow;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return findAll(nums, goal) - findAll(nums, goal - 1);
    }

    private int findAll(int[] nums, int goal) {
        int left = 0;
        int right = 0;
        int res = 0;
        int cur = 0;
        while (right < nums.length) {
            cur += nums[right];

            while (cur > goal && left <= right) {
                cur -= nums[left];
                left++;
            }

            int window = right - left + 1;
            res += window;
            right++;
        }
        return res;
    }
}