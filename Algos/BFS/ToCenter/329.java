package BFS.ToCenter;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int direction[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int degree[][] = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = 0;
                for (int[] dir : direction) {
                    int row = i + dir[0];
                    int col = j + dir[1];

                    if (row >= 0 && col >= 0 && row < n && col < m) {
                        if (matrix[row][col] < matrix[i][j])
                            count++;
                    }
                }
                degree[i][j] = count;
                if (count == 0)
                    q.offer(new int[] { i, j });
            }
        }

        // Kahn's algo starts

        int level = 0;
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                for (int[] dir : direction) {
                    int x = r + dir[0];
                    int y = c + dir[1];

                    if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[x][y] > matrix[r][c]) {
                        degree[x][y]--;
                        if (degree[x][y] == 0) {
                            q.offer(new int[] { x, y });
                        }
                    }
                }
            }
            level++;
        }

        return level;
    }
}