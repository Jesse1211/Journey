package TwoPointer.同向;

class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                nums[slow] = nums[index];
                slow++;
            }
            index++;
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }
}