package TwoPointer;

class Solution {
    public boolean judgeSquareSum(int c) {
        long min = 0;
        long max = (long) Math.sqrt(c);
        while (min <= max) {
            long cur = min * min + max * max;
            if (cur == c) {
                return true;
            } else if (cur < c) {
                min++;
            } else {
                max--;
            }
        }
        return false;
    }
}