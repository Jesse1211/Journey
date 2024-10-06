package Sub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 10) {
            return res;
        }

        HashMap<String, Integer> map = new HashMap<>();
        String cur = "";
        for (int i = 0; i < 10; i++) {
            cur += s.charAt(i);
        }

        map.put(cur, map.getOrDefault(cur, 0) + 1);

        for (int i = 1; i < s.length() - 9; i++) {
            StringBuilder sb = new StringBuilder(cur);
            sb.deleteCharAt(0);
            sb.append(s.charAt(i + 9));
            cur = sb.toString();
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        for (String each : map.keySet()) {
            if (map.get(each) > 1) {
                res.add(each);
            }
        }
        return res;
    }
}