package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        int[][] costs = new int[m][n];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);

        for (int[] c : costs) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }

        costs[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] dir : directions) {
                int x = cur[0];
                int y = cur[1];
                int newCost = costs[x][y];

                // 一直走到墙或者到头
                while (x + dir[0] >= 0 &&
                        y + dir[1] >= 0 &&
                        x + dir[0] < m &&
                        y + dir[1] < n &&
                        maze[x + dir[0]][y + dir[1]] == 0) {
                    x += dir[0];
                    y += dir[1];
                    newCost++;
                }

                // 上一步是否访问过
                if (costs[x][y] > newCost) {
                    q.add(new int[] { x, y });
                    costs[x][y] = newCost;
                }
            }
        }

        return costs[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : costs[destination[0]][destination[1]];
    }
}