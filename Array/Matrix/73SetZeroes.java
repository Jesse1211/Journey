package Array.Matrix;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<Integer>();
        Set<Integer> col = new HashSet<Integer>();
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        for (var i : row) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = 0;
            }
        }

        for (var i : col) {
            for (int j = 0; j < r; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}