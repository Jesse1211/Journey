package Array.BinarySearchInclusive;

class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
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
                } else {
                    return true;
                }
                if (nums[right] != target) {
                    right--;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}

// [4,5,0,1,2]