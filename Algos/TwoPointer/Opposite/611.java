package Opposite;

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) { // 长 + 长 > 短
                    res += right - left; // 包含了所有的组合
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
