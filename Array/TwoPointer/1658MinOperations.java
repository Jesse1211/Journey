package TwoPointer;

class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (var num : nums) {
            sum += num;
        }
        int y = sum - x;

        int slow = 0;
        int cur = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            while (cur > y && slow <= i) {
                cur -= nums[slow++];
            }
            if (cur == y) {
                res = Math.max(res, i - slow + 1);
            }
        }

        return res == -1 ? -1 : nums.length - res;
    }
}