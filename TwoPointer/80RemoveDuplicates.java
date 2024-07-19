package TwoPointer;

// TODO: 这个很难想到，需要多做几遍
class Solution {
    public int removeDuplicates(int[] nums) {

        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}