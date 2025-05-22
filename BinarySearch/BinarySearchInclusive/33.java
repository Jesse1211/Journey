package BinarySearchInclusive;

/*
 * highlight: 要有structural thinking, 先判断mid, 然后判断mid和左右点的联系
 */
class Solution {
    public int search(int[] nums, int target) {

        // nums = [2,3,1], target = 1
        // nums = [2,3,1], target = 2
        // nums = [2,3,1], target = 3

        // nums = [3,1,2], target = 1
        // nums = [3,1,2], target = 2
        // nums = [3,1,2], target = 3

        // nums = [1,2,3], target = 1
        // nums = [1,2,3], target = 2
        // nums = [1,2,3], target = 3

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] > nums[right]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // mid is lowest number
                if (nums[left] != target) {
                    left++;
                } else {
                    right--;
                }
            }

        }

        return -1;
    }
}