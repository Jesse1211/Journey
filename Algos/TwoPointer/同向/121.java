package 同向;

class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int slow = 0;
        int fast = 0;

        while (fast < prices.length) {
            int save = prices[fast] - prices[slow];

            if (save < 0) {
                // prices[fast] < prices[slow], a better time to buy
                slow = fast;
                fast++;
            } else {
                res = Math.max(res, save);
                fast++;
            }
        }

        return res;
    }
}
