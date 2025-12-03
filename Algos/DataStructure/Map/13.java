package DataStructure.Map;

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
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (i != s.length() - 1 && map.get(cur) < map.get(s.charAt(i + 1))) {
                // 如果右边的数字比当前的大, 那么当前的就是负数.
                // note: I后面比I大的一定是V / X
                res -= map.get(cur);
            } else {
                res += map.get(cur);
            }
        }

        return res;
    }
}