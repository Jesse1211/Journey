package 同向;

class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int left = 0;

        for (int i = 0; i < prices.length; i++) {
            int profit = prices[i] - prices[left];
            if (profit > 0) {
                res = Math.max(profit, res);
            } else {
                left = i;
            }
        }
        return res;
    }
}