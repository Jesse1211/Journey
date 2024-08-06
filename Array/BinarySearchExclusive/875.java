package Array.BinarySearchExclusive;

// ”纵向“ 选取mid
//      left是最小值, right是最大值, 那就看看mid能不能吃
//      如果mid能吃, 试试更小的, 否则试试多吃点

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int min = 1;
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        while (min < max) {
            int mid = min + (max - min) / 2;
            if (check(piles, mid, h)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    private boolean check(int[] piles, int k, int h) {
        int hours = 0;
        for (int pile : piles) {
            hours += pile / k;

            if (pile % k > 0) {
                hours++;
            }
        }
        return hours <= h;
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
