package Array.Matrix;

// highlight: matrix里面找中间值, 这个case里面是右上角
// 如果matrix[i][j] == target, return true
// 如果matrix[i][j] < target, i++ 下一行
// 如果matrix[i][j] > target, j-- 左一列
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Start from the top-right corner
        // go bot if < target; go left if > target
        int row = 0;
        int col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target)
                return true;
            if (col - 1 >= 0 && matrix[row][col - 1] < target) {
                row++;
            } else {
                if (col - 1 < 0)
                    row++;
                else
                    col--;
            }
        }
        return false;
    }
}