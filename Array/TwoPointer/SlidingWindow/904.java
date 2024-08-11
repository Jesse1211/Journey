package TwoPointer.SlidingWindow;

import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {
        int res = 0;
        int slow = 0;
        int fast = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (fast < fruits.length) {
            map.put(fruits[fast], map.getOrDefault(fruits[fast], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[slow], map.get(fruits[slow]) - 1);
                if (map.get(fruits[slow]) == 0) {
                    map.remove(fruits[slow]);
                }
                slow++;
            }
            res = Math.max(res, fast - slow + 1);
            fast++;
        }
        return res;
    }
}