// 2. BFS  2d arrary，每个格子有一滴水，水会流向最低的neighbour, 判断最后形成的池塘的水量和池塘数量 

import java.util.Arrays;

public class 池塘 {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4, 5 },
                { 16, 17, 18, 19, 6 },
                { 15, 24, 25, 20, 7 },
                { 14, 23, 22, 21, 8 },
                { 13, 12, 11, 10, 9 }
        };
        System.out.println(Arrays.toString(pondSizes(matrix)));
    }

    public static int[] pondSizes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] res = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[index] = dfs(matrix, visited, i, j);
                index++;
            }
        }
        return res;
    }

    private static int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j] || matrix[i][j] != 0) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(matrix, visited, i + 1, j) + dfs(matrix, visited, i - 1, j) + dfs(matrix, visited, i, j + 1)
                + dfs(matrix, visited, i, j - 1) + dfs(matrix, visited, i + 1, j + 1)
                + dfs(matrix, visited, i + 1, j - 1) + dfs(matrix, visited, i - 1, j + 1)
                + dfs(matrix, visited, i - 1, j - 1);
    }

}
