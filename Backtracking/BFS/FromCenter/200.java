package Backtracking.BFS.FromCenter;

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
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
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
        while (!q.isEmpty()) {
            int level = q.size();

            for (int k = 0; k < level; k++) {

                int[] cur = q.poll();
                int curI = cur[0];
                int curJ = cur[1];

                if (curI < 0 || curI >= grid.length || curJ < 0 || curJ >= grid[0].length) {
                    continue;
                }

                if (grid[curI][curJ] == '0') {
                    continue;
                }

                grid[curI][curJ] = '0';
                q.offer(new int[] { curI + 1, curJ });
                q.offer(new int[] { curI - 1, curJ });
                q.offer(new int[] { curI, curJ + 1 });
                q.offer(new int[] { curI, curJ - 1 });
            }

        }

    }
}