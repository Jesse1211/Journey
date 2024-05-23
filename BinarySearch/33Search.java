package BinarySearch;

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[right] != target && nums[left] != target) {
                right--;
                left++;
            }
            else if (nums[right] == target) {
                return right;
            }
            else if (nums[left] == target) {
                return left;
            }
        }
        return -1;
    }
}