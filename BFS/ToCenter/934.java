package BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int shortestBridge(int[][] grid) {
        int[][] DIRS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        outer: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] { i, j });
                    grid[i][j] = 2; // avoid inf loop
                    break outer; // 两层loop,都要break
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] dir : DIRS) {
                int newR = cur[0] + dir[0];
                int newC = cur[1] + dir[1];

                if (newR < 0 || newC < 0 || newR >= n || newC >= m) {
                    continue;
                }

                if (grid[newR][newC] == 1) {
                    q.offer(new int[] { newR, newC });
                    grid[newR][newC] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] { i, j });
                }
            }
        }

        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();

                for (int[] dir : DIRS) {
                    int newR = cur[0] + dir[0];
                    int newC = cur[1] + dir[1];

                    if (newR < 0 || newC < 0 || newR >= n || newC >= m) {
                        continue;
                    }

                    if (grid[newR][newC] == 1) {
                        return res;
                    }

                    if (grid[newR][newC] == 0) {
                        q.offer(new int[] { newR, newC });
                        grid[newR][newC] = -1;
                    }
                }
            }
            res++;
        }

        return -1;
    }
}