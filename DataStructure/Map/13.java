package DataStructure.Heap;

import java.util.*;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;

        int index = 0;

        while (index < s.length()) {
            int cur = map.get(s.charAt(index));
            if ((index < s.length() - 1) && (cur < map.get(s.charAt(index + 1)))) {
                res -= cur;
            } else {
                res += cur;
            }

            index++;
        }

        return res;
    }
}