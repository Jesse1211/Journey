package Backtracking.BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历, 因为是matrix, 需要boolean[][] visited防止重复访问
 * 2. 用queue保存信息:
 *      保存将要访问的点
 * 3. 用queue更新信息:
 *      遍历所有相邻的点, 把符合条件的点加入queue并且改变在visited中的数值, 因为res是根据visited来的
 */

class Solution {
    int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int x = heights.length;
        int y = heights[0].length;

        boolean[][] PacificHeights = new boolean[x][y];
        boolean[][] AtlanticHeights = new boolean[x][y];

        Queue<int[]> PQ = new ArrayDeque<>();
        Queue<int[]> AQ = new ArrayDeque<>();

        for (int i = 0; i < x; i++) {
            PQ.offer(new int[] { i, 0 });
            PacificHeights[i][0] = true;
            AQ.offer(new int[] { i, y - 1 });
            AtlanticHeights[i][y - 1] = true;
        }

        for (int i = 0; i < y; i++) {
            PQ.offer(new int[] { 0, i });
            PacificHeights[0][i] = true;
            AQ.offer(new int[] { x - 1, i });
            AtlanticHeights[x - 1][i] = true;
        }

        bfs(heights, PQ, PacificHeights);
        bfs(heights, AQ, AtlanticHeights);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (PacificHeights[i][j] && AtlanticHeights[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, Queue<int[]> q, boolean[][] visited) {
        int x = heights.length;
        int y = heights[0].length;

        while (!q.isEmpty()) {
            var cur = q.poll();
            for (int[] dir : dirs) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];

                if (newX >= 0 && newY >= 0 && newX < x && newY < y && !visited[newX][newY]
                        && heights[newX][newY] >= heights[cur[0]][cur[1]]) {
                    visited[newX][newY] = true;
                    q.offer(new int[] { newX, newY });
                }

            }

        }

    }
}