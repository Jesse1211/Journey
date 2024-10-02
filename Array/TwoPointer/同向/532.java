package TwoPointer.同向;

import java.util.Arrays;

// highlight: 可以sort也可以用hashmap解决
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        Arrays.sort(nums);
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] - nums[slow] == k && slow != fast) {
                res++;
                while (fast + 1 < nums.length && nums[fast] == nums[fast + 1]) {
                    // skip duplicates
                    fast++;
                }
                fast++;
            } else if (nums[fast] - nums[slow] > k) {
                slow++;
            } else {
                fast++;
            }
        }
        return res;
    }
}