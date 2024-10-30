
import java.util.Arrays;

class Solution {
    /*
     * 从右到左遍历，找到第一个降序 A[x]
     * 从右到左遍历，找到第一个比 A[x]大的数字 A[y]
     * 交换 A[x]和 A[y]
     * 将 x 之后的数字排序
     */
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