package TwoPointer;

import java.util.Arrays;

// TODO: 这个很难想到，需要多做几遍
class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 1)
            return 0;

        Arrays.sort(nums);

        int left = 0, res = 0, used = Integer.MAX_VALUE;

        for (int right = 1; right < nums.length; right++) {
            while (left < right && nums[right] - nums[left] > k)
                left++;

            if (left != right && used != nums[left] && nums[right] - nums[left] == k) {
                res++;
                used = nums[left];
            }
        }
        return res;
    }
}