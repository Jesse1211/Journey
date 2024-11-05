package BinarySearchExclusive;

class Solution {
    public int minimumOperations(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = 0;

        int leftSum = nums[left];
        int rightSum = nums[right];
        while (left < right) {

            if (leftSum == rightSum) {
                left++;
                right--;
                leftSum = nums[left];
                rightSum = nums[right];
            }

            else if (leftSum > rightSum) {
                res++;
                right--;
                rightSum += nums[right];
            } else {
                res++;
                left++;
                leftSum += nums[left];
            }
        }

        return res;
    }
}