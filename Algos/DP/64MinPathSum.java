package DP;

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    memo[i][j] = grid[i][j];
                } else if (i == 0) {
                    memo[i][j] = grid[i][j] + memo[i][j - 1];
                } else if (j == 0) {
                    memo[i][j] = grid[i][j] + memo[i - 1][j];
                } else {
                    memo[i][j] = grid[i][j] + Math.min(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        return memo[m - 1][n - 1];
    }
}