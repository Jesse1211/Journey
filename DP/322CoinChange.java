package DP;

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] res = new int[amount + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && res[i - coins[j]] != Integer.MAX_VALUE) {
                    res[i] = Math.min(res[i], 1 + res[i - coins[j]]);
                }
            }
        }
        return res[amount] != Integer.MAX_VALUE ? res[amount] : -1;
    }
}