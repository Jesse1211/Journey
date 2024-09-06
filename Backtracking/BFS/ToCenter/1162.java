package Backtracking.BFS.ToCenter;

import java.util.*;

class Solution {
    public int maxDistance(int[][] grid) {
        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] { i, j });
                }
            }
        }

        int res = -1;
        while (!q.isEmpty()) {
            res++;
            int level = q.size();
            for (int i = 0; i < level; i++) {
                int[] cur = q.poll();
                int row = cur[0];
                int col = cur[1];
                for (int[] dir : directions) {
                    int newR = row + dir[0];
                    int newC = col + dir[1];
                    if (newR < grid.length && newC < grid[0].length && newR >= 0 && newC >= 0) {
                        if (grid[newR][newC] == 0) {
                            grid[newR][newC] = 1;
                            q.offer(new int[] { newR, newC });
                        }
                    }
                }
            }
        }
        return res == 0 ? -1 : res;
    }
}