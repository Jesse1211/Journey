package BinarySearchInclusive;

/*
 * highlight: 二分查找matrix 
 * - 可以从每行的最右边查找, 但是不够快
 * - 从最中间找! 或者从对角线找, 但是不知道怎么写
 */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int mid_val = matrix[mid / n][mid % n];

            if (mid_val == target)
                return true;
            else if (mid_val < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}