package Backtracking.BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

/* highlight: 这个难
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历, 因为是matrix, 需要boolean[][] visited防止重复访问
 * 2. 用queue保存信息:
 *    保存将要访问的点
 * 3. 用queue更新信息:
 *   遍历所有相邻的点, 把符合条件的点加入queue并且改变数值
 */

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {

            int level = q.size();
            for (int i = 0; i < level; i++) {

                int[] cur = q.poll();
                int row = cur[0];
                int col = cur[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow < 0 || newCol < 0 || newRow >= mat.length || newCol >= mat[0].length) {
                        continue;
                    }

                    if (visited[newRow][newCol] == true) {
                        continue;
                    }

                    mat[newRow][newCol] = mat[row][col] + 1;
                    visited[newRow][newCol] = true;
                    q.offer(new int[] { newRow, newCol });

                }
            }
        }
        return mat;
    }
}