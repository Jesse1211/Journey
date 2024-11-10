package SlidingWindow;

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // find the min substring with length of cardPoints.length - k
        int total = 0;
        for (int c : cardPoints) {
            total += c;
        }

        int res = Integer.MAX_VALUE;
        int slow = 0;
        int fast = 0;
        int sum = 0;

        while (fast < cardPoints.length - k) {
            sum += cardPoints[fast++];
        }
        res = sum;

        while (fast < cardPoints.length) {
            sum -= cardPoints[slow++];
            sum += cardPoints[fast++];
            res = Math.min(res, sum);
        }
        return total - res;
    }
}