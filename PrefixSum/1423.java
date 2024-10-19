package PrefixSum;

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // find the min substring with length of cardPoints.length - k
        int[] sums = new int[cardPoints.length];
        for (int i = 0; i < cardPoints.length; i++) {
            if (i == 0) {
                sums[i] = cardPoints[i];
            } else {
                sums[i] = sums[i - 1] + cardPoints[i];
            }
        }

        int size = cardPoints.length - k;
        if (size == 0) {
            return sums[k - 1];
        }

        int res = Integer.MAX_VALUE;
        int slow = 0;
        int fast = size - 1;
        while (fast < cardPoints.length) {
            res = Math.min(res, sums[fast] - sums[slow] + cardPoints[slow]);
            fast++;
            slow++;
        }
        return sums[cardPoints.length - 1] - res;

    }
}