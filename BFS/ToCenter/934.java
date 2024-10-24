package BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int[] first = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    first[0] = i;
                    first[1] = j;
                    break;
                }
            }
        }

        Queue<int[]> q1 = new ArrayDeque<>(); // first traverse to find all position from land #1
        Queue<int[]> q2 = new ArrayDeque<>(); // second traverse to find best path to land #2
        q1.offer(first);
        q2.offer(first);
        grid[first[0]][first[1]] = 2; // avoid inf loop

        while (!q1.isEmpty()) {
            int[] cur = q1.poll();
            for (int[] next : directions) {
                int curX = next[0] + cur[0];
                int curY = next[1] + cur[1];
                if (curX >= 0 && curX < n && curY >= 0 && curY < n && grid[curX][curY] == 1) {
                    q1.offer(new int[] { curX, curY });
                    q2.offer(new int[] { curX, curY });
                    grid[curX][curY] = 2;
                }
            }
        }

        int res = 0;
        while (!q2.isEmpty()) {
            int level = q2.size();
            while (level-- > 0) {
                int[] cur = q2.poll();
                for (int[] next : directions) {
                    int curX = next[0] + cur[0];
                    int curY = next[1] + cur[1];
                    if (curX >= 0 && curX < n && curY >= 0 && curY < n) {
                        if (grid[curX][curY] == 1) {
                            return res;
                        } else if (grid[curX][curY] == 0) {
                            q2.add(new int[] { curX, curY });
                            grid[curX][curY] = -1;
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}