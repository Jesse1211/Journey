package Array;

class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int buy = prices[0];
        int cell = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
                cell = 0;
            } else if (prices[i] > cell) {
                cell = prices[i];
            }
            res = Math.max(cell - buy, res);
        }
        return res;
    }
}