package Array.BinarySearchExclusive;

/*
 * highlight: binary search的helper function需要想好怎么算比较简单
 */

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = Integer.MAX_VALUE;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (beltIsBroken(mid, weights, days)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean beltIsBroken(int capacity, int[] weights, int days) {

        int approxDays = 1;
        int loaded = 0;
        for (int weight : weights) {
            if (weight > capacity) {
                return true;
            }
            if (loaded + weight > capacity) {
                approxDays++;
                loaded = weight;
            } else {
                loaded += weight;
            }
        }
        return approxDays > days;

    }
}