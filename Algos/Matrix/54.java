package Matrix;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;

        int rowTop = 0;
        int colLeft = 0;
        int rowBot = n - 1;
        int colRight = m - 1;

        int cur;
        while (rowTop <= rowBot && colLeft <= colRight) {
            // top
            for (int i = colLeft; i <= colRight; i++) {
                cur = matrix[rowTop][i];
                res.add(cur);
            }
            rowTop++;

            // right
            for (int i = rowTop; i <= rowBot; i++) {
                cur = matrix[i][colRight];
                res.add(cur);
            }
            colRight--;

            if (!(rowTop <= rowBot && colLeft <= colRight)) {
                break;
            }

            // down
            for (int i = colRight; i >= colLeft; i--) {
                cur = matrix[rowBot][i];
                res.add(cur);
            }
            rowBot--;

            // left
            for (int i = rowBot; i >= rowTop; i--) {
                cur = matrix[i][colLeft];
                res.add(cur);
            }
            colLeft++;
        }
        return res;
    }
}