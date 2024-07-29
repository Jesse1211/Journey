package Matrix;

/* highlight: 在matrix的时候, 一般是dfs四个方向, 但是在list的时候, 一般是dfs两个方向, 并且如果只是改data, 一般不需要res
 * 1. 判定dfs的parameters: board, i, j
 *      (这里只是改数据, 所以不需要res)
 *      当前candidates取决于board[i][j]
 *      层层更新board, 最终board中的'O'变成'X', 'K'变成'O'
 * 2. dfs 何时停止: out of bound || board[i][j] == 'X' || board[i][j] == 'K'
 * 3. dfs 如何更新: board[i][j] == 'O'时, board[i][j] = 'K', 然后dfs四个方向
 */

class Solution {
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // left & right edges
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }

        // top & bottom edges
        for (int i = 0; i < cols; i++) {
            dfs(board, 0, i);
            dfs(board, rows - 1, i);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'K') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        if (board[row][col] == 'X') {
            return;
        }

        if (board[row][col] == 'O') {
            board[row][col] = 'K';
            dfs(board, row + 1, col);
            dfs(board, row - 1, col);
            dfs(board, row, col + 1);
            dfs(board, row, col - 1);
        }
    }
}