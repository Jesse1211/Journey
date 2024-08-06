package Array.BinarySearchInclusive;

class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid]) {
                left += 1;
                continue;
            }
            if (nums[right] == nums[mid]) {
                right -= 1;
                continue;
            }

            else if (nums[mid] < nums[right]) { // 2,0,1
                if (target >= nums[mid] && target <= nums[right]) { // take right
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] > nums[left]) { // 0,1,2
                if (target <= nums[mid] && target >= nums[left]) { // take left
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }
}