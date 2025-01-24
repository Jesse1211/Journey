package BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

/* highlight: matrix刷的还是不够
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历, 因为是matrix, 需要boolean[][] visited防止重复访问
 * 2. 用queue保存信息:
 *    保存将要访问的点
 * 3. 用queue更新信息:
 *   遍历所有相邻的点, 把符合条件的点加入queue并且改变数值
 */

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] DIRS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int[] dir : DIRS) {
                int newR = dir[0] + r;
                int newC = dir[1] + c;

                if (newR >= 0 && newC >= 0 && newR < n && newC < m && visited[newR][newC] == false) {
                    mat[newR][newC] = mat[r][c] + 1;
                    visited[newR][newC] = true;
                    q.offer(new int[] { newR, newC });
                }
            }
        }

        return mat;
    }
}