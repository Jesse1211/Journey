package Matrix;

/*
 * 1. 判定dfs的parameters: grid, i, j
*      (这里只是改数据, 所以不需要res)
*      当前candidates取决于grid[i][j]
*      层层更新grid, 最终grid中的'1'变成'0'
 * 2. dfs 何时停止: out of bound || grid[i][j] == '0'
 * 3. dfs 如何更新: grid[i][j] == '1'时, grid[i][j] = '0', 然后dfs四个方向
 */

class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(i, j, grid);
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // mark as visited
        dfs(i + 1, j, grid); // down
        dfs(i - 1, j, grid); // up
        dfs(i, j + 1, grid); // right
        dfs(i, j - 1, grid); // left
    }
}