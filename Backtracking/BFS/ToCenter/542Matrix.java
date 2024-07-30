package Backtracking.BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return new int[0][0];

        int m = mat.length, n = mat[0].length;
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = Integer.MAX_VALUE;
                } else {
                    q.offer(new int[] { i, j });
                }
            }
        }

        while (!q.isEmpty()) {

            int[] cell = q.poll();
            for (int[] dir : directions) {
                int r = cell[0] + dir[0], c = cell[1] + dir[1];
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    if (mat[r][c] > mat[cell[0]][cell[1]] + 1) {
                        q.offer(new int[] { r, c });
                        mat[r][c] = mat[cell[0]][cell[1]] + 1;
                    }
                }

            }

        }
        return mat;

    }
}