package TwoPointer;

class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int buy = 0;
        int cell = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[buy]) {
                buy = i;
                cell = i;
            } else if (prices[i] > prices[cell]) {
                cell = i;
            }
            res = Math.max(res, prices[cell] - prices[buy]);
        }
        return res;
    }
}