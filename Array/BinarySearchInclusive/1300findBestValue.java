package Array.BinarySearchInclusive;

class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1;
        int right = x;
        while (left <= right) {
            var mid = left + ( right - left ) / 2;
            // if (mid * mid > x) {
            if ((long) mid * mid > (long) x) {
                right = mid - 1;
            }
            else if (mid * mid == x) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return Math.round(right);
    }
}