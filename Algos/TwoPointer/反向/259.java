package 反向;

import java.util.Arrays;

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    res += right - left;
                    left++; //关键点, 从left到right所有情况加了以后, 更新left
                } else {
                    right -= 1;
                }
            }
        }

        return res;
    }
}