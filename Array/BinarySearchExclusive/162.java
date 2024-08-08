package Array.BinarySearchExclusive;

class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) { // left is larger
                right = mid;
            } else if (nums[mid] < nums[mid + 1]) { // right is larger
                left = mid + 1;
            } else {
                // return nums[right] > nums[left] ? right : left;
                right--;
                left++;
            }
        }
        return right;
    }
}