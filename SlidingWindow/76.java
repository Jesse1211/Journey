package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int[] res = new int[] { -1, 0, 0 };
        int slow = 0;
        int fast = 0;
        int uniques = 0;

        while (fast < s.length()) {
            char cur = s.charAt(fast);
            sMap.put(cur, sMap.getOrDefault(cur, 0) + 1);

            if (tMap.containsKey(cur) && sMap.get(cur).intValue() == tMap.get(cur).intValue()) {
                uniques++;
            }

            while (uniques >= tMap.size()) {
                char slowChar = s.charAt(slow);
                sMap.put(slowChar, sMap.get(slowChar).intValue() - 1);

                if (tMap.containsKey(slowChar) && sMap.get(slowChar).intValue() < tMap.get(slowChar).intValue()) {
                    uniques--;
                }
                if (fast - slow + 1 < res[0] || res[0] == -1) {
                    res = new int[] { fast - slow + 1, slow, fast };
                }
                slow++;
            }

            fast++;
        }

        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}