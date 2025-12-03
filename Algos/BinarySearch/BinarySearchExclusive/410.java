package BinarySearchExclusive;

class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;

        for (int num : nums) {
            left = Math.max(num, left);
            right += num;
        }

        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (works(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean works(int[] nums, int maxSum, int k) {
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            if (sum + num > maxSum) {
                sum = num;
                count++;
            } else {
                sum += num;
            }
        }

        return count <= k;
    }
}