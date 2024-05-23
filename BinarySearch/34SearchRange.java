package BinarySearch;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        return find(nums, target);
    }

    private int[] find(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] != target && nums[end] != target) {
                start++;
                end--;
            }
            else if (nums[start] != target && nums[end] == target){
                start++;
            }
            else if (nums[start] == target && nums[end] != target){
                end--;
            }
            else {
                return new int[]{start, end};
            }
        }
        return new int[]{-1, -1};
    }
}
