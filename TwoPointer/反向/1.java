package TwoPointer.反向;

import java.util.Arrays;

// highlight: 如果要sort, 记得用一个新的array来存储index

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[][] numsWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndex[i][0] = nums[i];
            numsWithIndex[i][1] = i;
        }

        Arrays.sort(numsWithIndex, (a, b) -> a[0] - b[0]);

        int left = 0, right = nums.length - 1;

        while (left < right) {
            if (numsWithIndex[left][0] + numsWithIndex[right][0] > target) {
                right--;
            } else if (numsWithIndex[left][0] + numsWithIndex[right][0] < target) {
                left++;
            } else {
                return new int[] { numsWithIndex[left][1], numsWithIndex[right][1] };
            }
        }
        return new int[] { numsWithIndex[left][1], numsWithIndex[right][1] };
    }
}