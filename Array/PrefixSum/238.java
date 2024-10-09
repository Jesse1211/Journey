package PrefixSum;

import java.util.Arrays;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // [1,2,3,4]
        // [1,1,2,6] // left to right
        // [24,12,8,6] // right to left
        int[] prod = new int[nums.length];
        Arrays.fill(prod, 1);

        // left to right
        for (int i = 1; i < nums.length; i++) {
            prod[i] = prod[i - 1] * nums[i - 1];
        }

        // right to left
        int sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            prod[i] = prod[i] * sum;
            sum *= nums[i];
        }
        return prod;

    }
}