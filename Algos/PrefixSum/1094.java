package PrefixSum;

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (int[] trip : trips) {
            int from = trip[1];
            int to = trip[2];

            start = Math.min(from, start);
            end = Math.max(to, end);
        }

        int[] path = new int[end - start + 1];
        for (int[] trip : trips) {
            int num = trip[0];
            int from = trip[1] - start;
            int to = trip[2] - start;

            path[from] += num;
            path[to] -= num;
        }

        int cur = 0;
        for (int p : path) {
            cur += p;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}