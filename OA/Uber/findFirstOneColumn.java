// 给你一个矩阵 只有01
// 1之后不会出现0
// 找第一个出现1的那一列
class Solution {
    public int findFirstOneColumn(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1; // 如果矩阵为空或者行列为0，返回-1
        }

        int row = 0;
        int col = matrix[0].length - 1;
        int firstCol = -1; // 初始化为-1，表示尚未找到1

        // 从右上角开始搜索
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == 1) {
                firstCol = col; // 记录当前列，因为这是到目前为止找到的最左边的1
                col--; // 向左移动
            } else {
                row++; // 向下移动
            }
        }

        return firstCol;
    }

    // public static void main(String[] args) {
    //     int[][] matrix = {
    //             { 0, 0, 0, 0 },
    //             { 0, 0, 0, 1 },
    //             { 0, 0, 1, 1 }
    //     };

    //     int firstOneColumn = findFirstOneColumn(matrix);
    //     System.out.println("第一个出现1的列是: " + firstOneColumn);
    // }
}
