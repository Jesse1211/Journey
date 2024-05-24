package DP;

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int side = triangle.size();
        int[][] res = new int[side + 1][side + 1];
        for (int i = side - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                res[i][j] = triangle.get(i).get(j) + Math.min(res[i + 1][j], res[i + 1][j + 1]);
            }
        }
        return res[0][0];
    }
}