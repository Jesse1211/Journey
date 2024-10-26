package 同向;

import java.util.Arrays;

// highlight: 可以sort也可以用hashmap解决
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        Arrays.sort(nums);
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (fast == slow) {
                fast++;
            } else if (nums[fast] - nums[slow] == k) {
                res++;
                fast++;
                while (fast < nums.length && nums[fast] == nums[fast - 1]) {
                    fast++;
                }
            } else if (nums[fast] - nums[slow] > k) {
                slow++;
            } else {
                fast++;
            }
        }
        return res;
    }
}