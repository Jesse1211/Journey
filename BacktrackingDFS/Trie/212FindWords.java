package BacktrackingDFS.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Trie + DFS
class SolutionFAST {
    private static final int[][] DIRS = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    class TrieNode {
        HashMap<Character, TrieNode> map;
        String word;

        public TrieNode() {
            map = new HashMap<>();
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return result;
        }

        int rows = board.length;
        int cols = board[0].length;

        TrieNode root = buildTrie(words, rows * cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (root.map.containsKey(board[i][j])) {
                    dfsHelper(board, root, i, j, result);
                }
            }
        }
        return result;
    }

    private TrieNode buildTrie(String[] words, int maxLen) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            if (w == null || w.length() == 0 || w.length() > maxLen) {
                continue;
            }
            TrieNode cur = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (!cur.map.containsKey(c)) {
                    cur.map.put(c, new TrieNode());
                }
                cur = cur.map.get(c);
            }
            cur.word = w;
        }
        return root;
    }

    private void dfsHelper(char[][] board, TrieNode cur, int x, int y, List<String> result) {
        if (cur == null) {
            return;
        }

        if (cur.word != null) {
            // A Valid word found. Add to the result.
            result.add(cur.word);
            // Set this word to null, so that its not added again.
            cur.word = null;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || !cur.map.containsKey(board[x][y])) {
            return;
        }

        char curChar = board[x][y];
        board[x][y] = '#';
        for (int[] d : DIRS) {
            dfsHelper(board, cur.map.get(curChar), x + d[0], y + d[1], result);
        }
        board[x][y] = curChar;
    }
}

class SolutionSLOW {
    boolean[][] visited;
    int n, m;

    public List<String> findWords(char[][] board, String[] words) {
        n = board.length;
        m = board[0].length;
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        visited = new boolean[n][m];
        for (String word : words) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (word.charAt(0) == board[i][j] && dfs(board, i, j, word, 0) && !set.contains(word)) {
                        res.add(word);
                        set.add(word);
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (word.length() == index) {
            return true;
        }

        if (i < board.length && i >= 0 && j < board[0].length && j >= 0 && board[i][j] == word.charAt(index)
                && visited[i][j] == false) {
            visited[i][j] = true;
            if (dfs(board, i + 1, j, word, index + 1) || dfs(board, i, j + 1, word, index + 1)
                    || dfs(board, i - 1, j, word, index + 1)
                    || dfs(board, i, j - 1, word, index + 1)) {
                visited[i][j] = false;
                return true;
            }
            visited[i][j] = false;
        }
        return false;
    }
}
