package Backtracking.BFS.FromCenter;

import java.util.*;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor == color) {
            return image; // No need to proceed if the starting point is already the target color
        }

        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { sr, sc });

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            image[cur[0]][cur[1]] = color;

            for (int[] dir : directions) {
                int curR = cur[0] + dir[0];
                int curC = cur[1] + dir[1];

                if (curR >= 0 && curC >= 0 && curR < image.length && curC < image[0].length) {
                    if (image[curR][curC] == originalColor) {
                        q.offer(new int[] { curR, curC });
                    }
                }
            }
        }
        return image;
    }
}