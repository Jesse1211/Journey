package SlidingWindow;

class Solution {
    public int minOperations(int[] nums, int x) {
        // find the window with sum of all sum - x

        int target = -x;
        for (int num : nums) {
            target += num;
        }

        int slow = 0;
        int fast = 0;
        int sum = 0;
        int res = Integer.MIN_VALUE;

        while (fast < nums.length) {
            sum += nums[fast];

            while (sum > target && slow <= fast) {
                sum -= nums[slow++];
            }

            if (sum == target) {
                res = Math.max(res, fast - slow + 1);
            }

            fast++;
        }

        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }
}