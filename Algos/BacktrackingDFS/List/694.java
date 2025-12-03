package BacktrackingDFS.List;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Set<String> visited = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, i, j, sb);
                    visited.add(sb.toString());
                }
            }
        }

        return visited.size();
    }

    private void dfs(int[][] grid, int originX, int originY, int i, int j, StringBuilder sb) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 0;
        sb.append((i - originX) + "" + (j - originY));

        dfs(grid, originX, originY, i + 1, j, sb);
        dfs(grid, originX, originY, i - 1, j, sb);
        dfs(grid, originX, originY, i, j + 1, sb);
        dfs(grid, originX, originY, i, j - 1, sb);
    }
}