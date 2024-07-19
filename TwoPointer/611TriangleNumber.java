package TwoPointer;

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        int result = 0;

        if (nums.length < 3)
            return result;

        Arrays.sort(nums);

        for (int i = 2; i < nums.length; i++) {
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[i] < (nums[left] + nums[right])) {
                    result += (right - left);
                    right--;
                } else
                    left++;
            }
        }
        return result;
    }
}