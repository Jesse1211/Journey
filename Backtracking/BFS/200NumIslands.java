package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    bfs(i, j, grid);
                }
            }
        }
        return res;
    }

    private void bfs(int i, int j, char[][] grid) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { i, j });
        while (!q.isEmpty()) {
            int level = q.size();
            for (int k = 0; k < level; k++) {
                var cur = q.poll();
                int row = cur[0];
                int col = cur[1];
                if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    q.offer(new int[] { row + 1, col });
                    q.offer(new int[] { row - 1, col });
                    q.offer(new int[] { row, col + 1 });
                    q.offer(new int[] { row, col - 1 });
                }
            }
        }
    }
}