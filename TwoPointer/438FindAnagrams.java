package TwoPointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return res;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }

        int toBeMatched = map.size();
        int start = 0, end = 0;

        while (end < sLen) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)) {
                int count = map.get(cur);
                if (count == 1) {
                    toBeMatched--;
                }
                map.put(cur, count - 1);
            }
            end++;

            if (end - start > pLen) {
                cur = s.charAt(start);
                if (map.containsKey(cur)) {
                    int count = map.get(cur);
                    if (count == 0) {
                        toBeMatched++;
                    }
                    map.put(cur, count + 1);
                }
                start++;
            }

            if (toBeMatched == 0) {
                res.add(start);
            }
        }
        return res;

    }
}