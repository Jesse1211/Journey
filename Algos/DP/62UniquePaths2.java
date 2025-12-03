package DP;

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    memo[i][j] = 0;
                }

                else if (i == 0 && j == 0) {
                    memo[i][j] = 1;
                } else if (i == 0) {
                    memo[i][j] = memo[i][j - 1];
                } else if (j == 0) {
                    memo[i][j] = memo[i - 1][j];
                } else {
                    memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
                }
            }
        }
        return memo[m - 1][n - 1];
    }
}