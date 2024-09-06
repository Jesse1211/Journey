
import java.util.Arrays;

/*
 * highlight:这...这个题目的思路是真的难想到
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // find the breaking point: 第一个比右边element要小的数字
        int index = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }

        // base case - no breakpoint found -- 最小的在最左边 -> reset by sort
        if (index == -1) {
            Arrays.sort(nums, 0, n);
            return;
        }

        // finding the greater but closest num 第一个比他大的值就是下次swap的对象
        for (int i = n - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }
        // Reversing the remaining array.
        Arrays.sort(nums, index + 1, n);
    }
}