package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public void solve(char[][] board) {
        Queue<int[]> q = new ArrayDeque<>();
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O')
                q.offer(new int[] { i, 0 });
            if (board[i][cols - 1] == 'O')
                q.offer(new int[] { i, cols - 1 });
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O')
                q.offer(new int[] { 0, j });
            if (board[rows - 1][j] == 'O')
                q.offer(new int[] { rows - 1, j });
        }

        while (!q.isEmpty()) {
            int level = q.size();
            for (int k = 0; k < level; k++) {
                var cur = q.poll();

                int i = cur[0];
                int j = cur[1];

                if (i >= 0 && j >= 0 && i <= board.length - 1 && j <= board[0].length - 1 && board[i][j] == 'O') {
                    board[i][j] = 'K';

                    q.offer(new int[] { i + 1, j });
                    q.offer(new int[] { i - 1, j });
                    q.offer(new int[] { i, j + 1 });
                    q.offer(new int[] { i, j - 1 });
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 'K') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

}