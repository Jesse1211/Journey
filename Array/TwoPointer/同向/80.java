package TwoPointer.同向;

// highlight: 这个很难想到，需要多做几遍
class Solution {
    public int removeDuplicates(int[] nums) {
        // [1,1,1,2,2,3]
        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}