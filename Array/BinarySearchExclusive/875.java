package BinarySearchExclusive;

// ”纵向“ 选取mid
//      left是最小值, right是最大值, 那就看看mid能不能吃
//      如果mid能吃, 试试更小的, 否则试试多吃点
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isEatable(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isEatable(int[] piles, int h, int speed) {
        for (int pile : piles) {
            h = h - pile / speed;
            if (pile % speed != 0) {
                h -= 1;
            }

        }
        return h >= 0;
    }
}

// [3,6,7,11] n = 4 h = 8
// 6, 7, 11
// 2, 7, 11
// 7, 11
// 3, 11
// 11
// 7
// 3
// .
