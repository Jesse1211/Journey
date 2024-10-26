package PrefixSum;

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];

        for (int[] book : bookings) {
            int first = book[0] - 1;
            int last = book[1];
            int count = book[2];
            res[first] += count;
            if (last < n) {
                // 如果最后再走就没必要了
                res[last] -= count;
            }
        }

        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
