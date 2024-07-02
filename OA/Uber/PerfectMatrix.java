// 给一个 0 和 1 组成的矩阵，要求最大 size 的 perfect 矩阵的 center 坐标
// （有多个的话，输出最小 row 的那个，同一个 row 有多个输出最小 column 的那个）。
// perfect 矩阵的定义就是以某个点为中心，它的两条对角线上所有的数字都为 1。
// perfect 矩阵的 size 就是对角线构成的正方形的宽度，
// 比如 (2, 4) 这个点为 1，它周围的点都为 0，那么这个perfect size 就是 1。

class Solution {
    public int[] perfectMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[2];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int size = 0;
                while (i - size >= 0 && i + size < m && j - size >= 0 && j + size < n) {
                    if (matrix[i - size][j - size] == 1 && matrix[i - size][j + size] == 1
                            && matrix[i + size][j - size] == 1 && matrix[i + size][j + size] == 1) {
                        size++;
                    } else {
                        break;
                    }
                }
                if (size > max) {
                    max = size;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}
