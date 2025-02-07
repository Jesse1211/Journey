package BinarySearchExclusive;

class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1;
        int right = maxSum;

        while (left < right) {
            int mid = left + (right - left) / 2 + 1; /// must use RoundUp因为在向右查找

            if (getSum(n, index, mid) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private long getSum(int n, int index, int cur) {
        long sum = 0;

        if (cur > index) {
            // index's left side won't have 0
            sum += (long) (cur + cur - index) * (index + 1) / 2;
        } else {
            sum += (long) (cur + 1) * cur / 2 + index - cur + 1;
        }

        if (cur >= n - index) {
            sum += (long) (cur + cur - n + 1 + index) * (n - index) / 2;
        } else {
            sum += (long) (cur + 1) * cur / 2 + n - index - cur;
        }

        return sum - cur;

        /*
         * // 2 pases to find sum, keep decreasing temp until 0
         * // 2 for loop is too slow, can use math
         * long sum = cur;
         * int temp = cur;
         * for (int i = index + 1; i < n; i++) {
         * if (temp > 1) {
         * temp--;
         * }
         * sum += temp;
         * }
         * 
         * temp = cur;
         * for (int i = index - 1; i >= 0; i--) {
         * if (temp > 1) {
         * temp--;
         * }
         * sum += temp;
         * }
         * return sum;
         */
    }
}