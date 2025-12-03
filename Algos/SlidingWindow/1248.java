package SlidingWindow;

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return findCount(nums, k) - findCount(nums, k - 1);
    }

    public int findCount(int[] nums, int k) {
        int res = 0;
        int slow = 0;
        int fast = 0;
        int cur = 0;
        while (fast < nums.length) {
            // expand
            if (nums[fast] % 2 == 1) {
                cur++;
            }

            // shrink
            while (cur > k) {
                if (nums[slow] % 2 == 1) {
                    cur--;
                }
                slow++;
            }
            res += fast - slow + 1;
            fast++;
        }
        return res;
    }
}