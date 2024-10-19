// package TwoPointer;

import java.util.Arrays;

/*
 * highlight: 感觉有很多做法 但是还是不太懂
 */

class Solution {
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums); // Sort the array first
        for (int i = nums.length - 1; i >= 2; i--) { // Iterate over the largest side
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) { // Check if it's a valid triangle
                    res += right - left; // All pairs between left and right are valid
                    right--; // Move right pointer to try a smaller second side
                } else {
                    left++; // Otherwise, move left pointer to try a larger first side
                }
            }
        }
        return res;
    }
}
