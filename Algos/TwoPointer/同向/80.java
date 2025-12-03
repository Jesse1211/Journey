package 同向;

// highlight: 这个很难想到，需要多做几遍
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }

        // init from first 'possible' invalid element
        int slow = 2; // index stores invalid element
        int fast = 2; // fast is always further than slow
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) { // ready to remove invalid element
                // if nums[slow - 2] == nums[fast]: its meaningless to swap, wait until fast is
                // the next value
                // fast is always the first number in 'same number group'
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}