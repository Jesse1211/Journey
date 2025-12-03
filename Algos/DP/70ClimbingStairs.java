package DP;

import java.util.Arrays;

class Solution {
    public int climbStairs(int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, 0);
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}