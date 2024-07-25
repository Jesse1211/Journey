package TwoPointer.SlidingWindow;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int slow = 0;
        int left = k;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                while (left == 0) {
                    if (nums[slow] == 0) {
                        left++;
                    }
                    slow++;
                }
                left--;

            }
            res = Math.max(res, i - slow + 1);

        }
        return res;
    }
}