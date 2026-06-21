package TwoPointer;

class Solution {
    public int minimumOperations(int[] nums) {
        int res = 0;
        int left = 0;
        int right = nums.length - 1;

        int leftNum = nums[left];
        int rightNum = nums[right];

        while (left < right) {
            if (leftNum == rightNum) {
                left++;
                right--;
                leftNum += nums[left];
                rightNum += nums[right];
                continue;
            }

            res++;
            if (leftNum < rightNum) {
                left++;
                leftNum += nums[left];
            } else {
                right--;
                rightNum += nums[right];
            }
        }

        return res;
    }
}