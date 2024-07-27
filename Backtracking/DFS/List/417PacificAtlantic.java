package List;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int r = heights.length;
        int c = heights[0].length;
        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];

        // traverse top and bottom of island
        for (int i = 0; i < c; i++) {
            dfs(0, i, pacific, heights[0][i], heights); // top row surrounds pacific ocean; 4: prevHeight=start height
            dfs(r - 1, i, atlantic, heights[r - 1][i], heights); // bottom row surrounds atlantic ocean
        }

        // traverse left and right of island
        for (int i = 0; i < r; i++) {
            dfs(i, 0, pacific, heights[i][0], heights); // left=pacific
            dfs(i, c - 1, atlantic, heights[i][c - 1], heights); // right=atlantic
        }

        // find common of pac and atl
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> li = new ArrayList<Integer>();
                    li.add(i);
                    li.add(j);
                    result.add(li);
                }
            }
        }
        return result;
    }

    private void dfs(int i, int j, boolean[][] visit, int prev, int[][] heights) {
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length || heights[i][j] < prev || visit[i][j]) {
            return;
        }
        visit[i][j] = true;
        dfs(i + 1, j, visit, heights[i][j], heights);
        dfs(i, j + 1, visit, heights[i][j], heights);
        dfs(i - 1, j, visit, heights[i][j], heights);
        dfs(i, j - 1, visit, heights[i][j], heights);
    }
}