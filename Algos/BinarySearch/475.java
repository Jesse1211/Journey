import java.util.Arrays;
import java.util.TreeSet;

/**
 * highlight: 这是好题
 */

class Solution1 { // Binary Search
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n = houses.length;
        int left = 0;
        int right = Integer.MAX_VALUE;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (coversAll(houses, heaters, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean coversAll(int[] houses, int[] heaters, int radius) {
        int heaterIndex = 0;
        for (int house : houses) {
            int curDis = Math.abs(house - heaters[heaterIndex]);

            while (curDis > radius) {
                heaterIndex++;

                if (heaterIndex == heaters.length) {
                    return false;
                }

                curDis = Math.abs(house - heaters[heaterIndex]);
            }
        }
        return true;
    }
}

class Solution2 { // Two pointers
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int heaterIndex = 0;
        int res = 0;

        for (int i = 0; i < houses.length; i++) {
            int house = houses[i];

            // find closest heater for current house
            while (heaterIndex < heaters.length - 1) {
                int cur = Math.abs(heaters[heaterIndex] - house);
                int next = Math.abs(heaters[heaterIndex + 1] - house);
                if (cur < next) {
                    break;
                }
                heaterIndex++;
            }

            res = Math.max(res, Math.abs(heaters[heaterIndex] - house));
        }

        return res;
    }
}

class Solution3 {
    // O(m logn)
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> heaterSet = new TreeSet<>();

        for (int h : heaters) {
            heaterSet.add(h);
        }

        int res = 0;
        for (int h : houses) {

            Integer left = heaterSet.floor(h);
            Integer right = heaterSet.ceiling(h);

            int d2 = left == null ? Integer.MAX_VALUE : h - left;
            int d1 = right == null ? Integer.MAX_VALUE : right - h;

            res = Math.max(res, Math.min(d1, d2));
        }

        return res;
    }
}
