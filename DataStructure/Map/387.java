import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> visited = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!map.containsKey(cur) && !visited.contains(cur)) {
                visited.add(cur);
                map.put(cur, i);
            } else {
                map.remove(cur);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i : map.values()) {
            System.out.println(i);
            res = Math.min(res, i);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}