package DP;

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int[][] DIRS = { { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // 第一行 / base case
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < n; i++) { // row
            for (int j = 0; j < n; j++) { // col

                int prev = Integer.MAX_VALUE;

                for (int[] dir : DIRS) {
                    int newRow = dir[0] + i;
                    int newCol = dir[1] + j;
                    if (newRow < 0 || newCol < 0 || newCol >= n) {
                        continue;
                    }
                    prev = Math.min(prev, dp[newRow][newCol]);
                }

                dp[i][j] = prev + matrix[i][j];
            }
        }

        int res = Integer.MAX_VALUE;

        // last row
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }

        return res;
    }
}