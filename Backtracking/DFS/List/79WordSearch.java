package List;

class Solution {
    public boolean exist(char[][] board, String word) {
        var numRow = board.length;
        var numCol = board[0].length;
        boolean[][] visited = new boolean[numRow][numCol];
        boolean result = false;

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result = dfs(i, j, board, word, 0, visited);
                    if (result)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, char[][] board, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] == true
                || board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        if (dfs(i + 1, j, board, word, index + 1, visited) ||
                dfs(i, j + 1, board, word, index + 1, visited) ||
                dfs(i - 1, j, board, word, index + 1, visited) ||
                dfs(i, j - 1, board, word, index + 1, visited)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}