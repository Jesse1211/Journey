package BinarySearchExclusive;

// 先抓左边, 再抓右边, 最后看有什么case没有过, 单独给结论

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) { // left side is good
                if (nums[right] > nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] > nums[right]) { // right side is good
                if (nums[right] < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[right] == nums[mid]) { // others
                if (nums[left] < nums[right]) {
                    right--;
                } else {
                    left++;
                }
            } else {
                right--;
            }
        }
        return nums[left];
    }
}