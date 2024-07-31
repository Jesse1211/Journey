package Backtracking.BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历
 * 2. 用queue保存信息:
 *     保存将要访问的点
 * 3. 用queue更新信息:
 *    遍历所有相邻的点, 把符合条件的点加入queue并且改变数值
 */

class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        // left & right
        for (int i = 0; i < n; i++) {
            bfs(board, i, 0);
            bfs(board, i, board[0].length - 1);
        }

        // top & bot
        for (int i = 0; i < m; i++) {
            bfs(board, 0, i);
            bfs(board, board.length - 1, i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'K') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { i, j });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curI = cur[0];
            int curJ = cur[1];

            if (curI < 0 || curJ < 0 || curI >= board.length || curJ >= board[0].length) {
                continue;
            }

            if (board[curI][curJ] == 'X' || board[curI][curJ] == 'K') {
                continue;
            }

            board[curI][curJ] = 'K';

            q.offer(new int[] { curI + 1, curJ });
            q.offer(new int[] { curI - 1, curJ });
            q.offer(new int[] { curI, curJ + 1 });
            q.offer(new int[] { curI, curJ - 1 });
        }
    }
}