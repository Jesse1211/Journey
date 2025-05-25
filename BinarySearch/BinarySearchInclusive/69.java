package BinarySearchInclusive;

class Solution {
    public int mySqrt(int x) {
        long left = 0;
        long right = (long) x + 1;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == x) { // (int / 2)^2 有可能会爆
                return (int) mid;
            }

            if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (left * left <= x) {
            return (int) left;
        }
        return (int) left - 1;
    }
}
