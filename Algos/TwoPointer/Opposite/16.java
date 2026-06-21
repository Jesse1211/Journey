package Opposite;

import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int cur = nums[i] + nums[left] + nums[right];
                if (cur < target) {
                    left++;
                } else if (cur > target) {
                    right--;
                } else {
                    return target;
                }
                if (Math.abs(res - target) > Math.abs(cur - target)) {
                    res = cur;
                }
            }
        }
        return res;

    }
}