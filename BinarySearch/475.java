package BinarySearch;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * highlight: 这是好题
 */
class Solution {
    // O(n logn)
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int index = 0;
        int res = 0;

        for (int i = 0; i < houses.length; i++) {
            int curr = houses[i];

            while (index < heaters.length - 1 &&
                    Math.abs(heaters[index] - curr) >= Math.abs(heaters[index + 1] - curr)) {
                index++;
            }

            res = Math.max(res, Math.abs(heaters[index] - curr));
        }

        return res;
    }
}

class Solution2 {
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

class Solution3 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = 0;

        for (int house : houses) {
            int index = binarySearch(heaters, house);

            int d1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int d2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            res = Math.max(res, Math.min(d1, d2));
        }

        return res;
    }

    private int binarySearch(int[] heaters, int target) {
        int left = 0, right = heaters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] == target) {
                return mid;
            } else if (heaters[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // closest heater index or insertion point
    }
}
