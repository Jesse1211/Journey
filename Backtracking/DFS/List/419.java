package List;

class Solution {
    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int countBattleships(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }

        if (board[i][j] != 'X') {
            return;
        }

        board[i][j] = 'Y';

        for (int[] dir : directions) {
            dfs(board, i + dir[0], j + dir[1]);
        }
    }
}