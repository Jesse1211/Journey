package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private int size;
    private List<List<String>> res = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
        size = n;

        char emptyBoard[][] = new char[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                emptyBoard[i][j] = '.';
            }
        }

        backtrack(
                0,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                emptyBoard);
        return res;
    }

    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<String>();
        for (int row = 0; row < size; row++) {
            String current_row = new String(state[row]);
            board.add(current_row);
        }

        return board;
    }

    private void backtrack(
            int row,
            Set<Integer> diagonals,
            Set<Integer> antiDiagonals,
            Set<Integer> cols,
            char[][] state) {
        // Base case - N queens have been placed
        if (row == size) {
            res.add(createBoard(state));
            return;
        }

        // go by column
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col; // y = -x + b. b代表当前xy位置
            int currAntiDiagonal = row + col; // y = x + b
            // If the queen is not placeable
            if (cols.contains(col) ||
                    diagonals.contains(currDiagonal) ||
                    antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            // "Add" the queen to the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            state[row][col] = 'Q';

            // next recursion, go by row
            backtrack(row + 1, diagonals, antiDiagonals, cols, state);

            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            state[row][col] = '.';
        }
    }
}