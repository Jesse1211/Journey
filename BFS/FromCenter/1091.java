package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int res = 0;
        if (grid[0][0] == 1) {
            return -1;
        }

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        q.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!q.isEmpty()) {
            res++;

            int level = q.size();

            for (int i = 0; i < level; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if (r == n - 1 && c == m - 1) {
                    return res;
                }

                for (int[] dir : directions) {
                    int newR = r + dir[0];
                    int newC = c + dir[1];

                    if (newR < 0 || newR >= n || newC < 0 || newC >= m || grid[newR][newC] == 1
                            || visited[newR][newC]) {
                        continue;
                    }

                    visited[newR][newC] = true;
                    q.offer(new int[] { newR, newC });
                }
            }
        }
        return -1;
    }
}