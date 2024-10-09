package PrefixSum;

class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sumFromLeft = new int[n];
        int[] sumFromRight = new int[n];
        sumFromLeft[0] = nums[0];
        sumFromRight[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            sumFromLeft[i] = sumFromLeft[i - 1] + nums[i];
            sumFromRight[n - i - 1] = sumFromRight[n - i] + nums[n - i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (sumFromLeft[i] == sumFromRight[i]) {
                return i;
            }
        }
        return -1;
    }
}