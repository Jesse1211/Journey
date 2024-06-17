package String;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            while (set.contains(cur)) {
                set.remove(s.charAt(left++));
            }
            set.add(cur);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}