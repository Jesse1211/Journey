package BinarySearchInclusive;

class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1;
        int right = x;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cur = mid * mid;
            if (cur > (long) x) {
                right = (int) mid - 1;
            } else if (cur == (long) x) {
                return (int) mid;
            } else {
                left = (int) mid + 1;
            }
        }
        return Math.round(right);
    }
}