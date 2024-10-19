package BinarySearchInclusive;

/*
 * highlight: 要有structural thinking, 先判断mid, 然后判断mid和左右点的联系
 */

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // mid is maximum
            if (nums[mid] >= nums[right]) { // check right
                if (target < nums[mid] && nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] >= nums[left]) { // check left
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

                // mid is minimum
            } else {
                if (nums[left] != target) {
                    left++;
                }
                if (nums[right] != target) {
                    right--;
                }
            }
        }
        return -1;
    }
}

// [4,5,0,1,2]