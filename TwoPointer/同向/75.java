package TwoPointer.同向;

// highlight: 正向反向双指针
class Solution {
    public void sortColors(int[] nums) {
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        int index = 0;
        while (index <= twoIndex) {

            if (nums[index] == 0) {
                swap(nums, index, zeroIndex);
                zeroIndex++;
                index++;
            } else if (nums[index] == 2) {
                swap(nums, index, twoIndex);
                twoIndex--;
            } else {
                index++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}