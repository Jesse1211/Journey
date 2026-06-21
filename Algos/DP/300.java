package DP;

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] sorted = new int[nums.length];
        Arrays.fill(sorted, Integer.MAX_VALUE);

        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(sorted, nums[i]);
            sorted[index] = nums[i];
        }

        int res = 0;
        for (int num : sorted) {
            if (num != Integer.MAX_VALUE) {
                res++;
            }
        }
        return res;
    }

    private int binarySearch(int[] nums, int num) {
        // insert the num to i where nums[i-1] is stricktly smaller than num

        // [10,9,2,5,3,7,101,18]
        // [2,3,7,18,MAX,MAX,MAX,MAX]
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == num) {
                return mid;
            }

            if (nums[mid] < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}