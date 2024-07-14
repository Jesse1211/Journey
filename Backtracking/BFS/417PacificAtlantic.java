package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int x = heights.length;
        int y = heights[0].length;

        boolean[][] PacificHeights = new boolean[x][y];
        boolean[][] AtlanticHeights = new boolean[x][y];

        Queue<int[]> PQ = new ArrayDeque<>();
        Queue<int[]> AQ = new ArrayDeque<>();

        for (int i = 0; i < x; i++) {
            PQ.offer(new int[] { i, 0 });
            PacificHeights[i][0] = true;
            AQ.offer(new int[] { i, y - 1 });
            AtlanticHeights[i][y - 1] = true;
        }

        for (int i = 0; i < y; i++) {
            PQ.offer(new int[] { 0, i });
            PacificHeights[0][i] = true;
            AQ.offer(new int[] { x - 1, i });
            AtlanticHeights[x - 1][i] = true;
        }

        bfs(heights, PQ, PacificHeights);
        bfs(heights, AQ, AtlanticHeights);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (PacificHeights[i][j] && AtlanticHeights[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, Queue<int[]> q, boolean[][] visited) {
        int x = heights.length;
        int y = heights[0].length;

        while (!q.isEmpty()) {
            var cur = q.poll();
            for (int[] dir : dirs) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];

                if (newX >= 0 && newY >= 0 && newX < x && newY < y && !visited[newX][newY]
                        && heights[newX][newY] >= heights[cur[0]][cur[1]]) {
                    visited[newX][newY] = true;
                    q.offer(new int[] { newX, newY });
                }

            }

        }

    }
}