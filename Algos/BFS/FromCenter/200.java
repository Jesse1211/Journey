package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 1. 判定bfs的逻辑: 
*      从符合标准的任意点出发, 逐层遍历
*      通过改变数值来避免重复访问
 * 2. 用queue保存信息:
*      保存将要访问的点
 * 3. 用queue更新信息:
*      遍历所有相邻的点, 把符合条件的点加入queue并且改变数值
 */
class Solution {
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int numIslands(char[][] grid) {
        int res = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    bfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { i, j });
        grid[i][j] = '0';

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                        || grid[newRow][newCol] == '0') {
                    continue;
                }
                grid[newRow][newCol] = '0';
                q.offer(new int[] { newRow, newCol });
            }
        }
    }
}