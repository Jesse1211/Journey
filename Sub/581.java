package Sub;

// highlight - TODO: 这个很难想到，需要多做几遍
// 找到最左边和最右边的不符合升序的元素，然后计算他们之间的长度
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int left = 0;
        int right = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                left = i;
            }
        }
        return right - left + 1;

    }
}