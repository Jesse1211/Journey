package BinarySearch;

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {return nums[0];}
        int left = 0 ;
        int right = nums.length - 1;
        while (true) {
            if (nums[left] == nums[left+1] && nums[right] == nums[right-1]) {
                left += 2;
                right -= 2;
            } else if (nums[left] != nums[left+1]){
                return nums[left];
            }
             else if (nums[right] != nums[right-1]){
                return nums[right];
            }
        }
    }
}