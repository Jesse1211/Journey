package TwoPointer;

// TODO: 这个很难想到，需要多做几遍
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int end = -1, max = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] < max)
                end = i;
            else
                max = nums[i];
        }

        int start = 0, min = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > min)
                start = i;
            else
                min = nums[i];
        }

        return end - start + 1;
    }
}