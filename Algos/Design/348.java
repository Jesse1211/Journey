package Design;

/*
 * Category: Matrix / Design
 */
class TicTacToe {
    int n;
    int[][] board;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    public int move(int row, int col, int player) {
        this.board[row][col] = player;
        if (isWinning(row, col)) {
            return player;
        }
        return 0;
    }

    private boolean isWinning(int i, int j) {
        int cur = this.board[i][j];

        // check horizontal
        for (int curRow = 0; curRow < this.n; curRow++) {
            if (this.board[curRow][j] != cur) {
                break;
            } else if (curRow == this.n - 1) {
                return true;
            }
        }

        // check vertical
        for (int curCol = 0; curCol < this.n; curCol++) {
            if (this.board[i][curCol] != cur) {
                break;
            } else if (curCol == this.n - 1) {
                return true;
            }
        }

        // Check anti-diagonal (top-right to bottom-left)
        if (i + j == n - 1) { // Only check anti-diagonal if the move is on it
            boolean winDiag2 = true;
            for (int index = 0; index < n; index++) {
                if (this.board[index][n - 1 - index] != cur) {
                    winDiag2 = false;
                    break;
                }
            }
            if (winDiag2)
                return true;
        }

        // check diagonal - top left to bot right
        if (i == j) {
            for (int index = 0; index < this.n; index++) {
                if (this.board[index][index] != cur) {
                    break;
                } else if (index == this.n - 1) {
                    return true;
                }
            }
        }

        // check diagonal - top right to bot left
        if (i + j == this.n - 1) {
            for (int index = 0; index < this.n; index++) {
                if (this.board[index][this.n - index - 1] != cur) {
                    break;
                } else if (index == this.n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

class TicTacToe2 {
    int[] rows;
    int[] cols;
    // rows, cols这里代表的是当前行 / 列一共有多少个player1的棋子
    int diagonal;
    int antiDiagonal;
    int size;

    /** Initialize your data structure here. */
    public TicTacToe2(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        size = n;
    }

    public int move(int row, int col, int player) {
        if (check(row, col, player))
            return player;
        return 0;
    }

    private boolean check(int row, int col, int player) {
        int offset = player == 1 ? 1 : -1;
        rows[row] += offset;
        cols[col] += offset;
        if (row == col) {
            // top left to bottom right
            diagonal += offset;
        }
        if (row + col == size - 1) {
            // top right to bottom left
            antiDiagonal += offset;
        }
        int m = size * offset; // determine if the player wins
        return (rows[row] == m || cols[col] == m || diagonal == m || antiDiagonal == m);

    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */