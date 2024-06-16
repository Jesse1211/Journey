package Array.Matrix;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        int top = 0, down = matrix.length - 1, right = matrix[0].length - 1, left = 0;
        while (top <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            if (!(top <= down && left <= right)) {
                break;
            }

            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            down--;

            for (int i = down; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }

        return res;
    }
}