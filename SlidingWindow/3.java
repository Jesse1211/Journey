package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> visited = new HashSet<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char cur = s.charAt(right++);

            while (visited.contains(cur)) {
                visited.remove(s.charAt(left++));
            }
            visited.add(cur);
            res = Math.max(right - left, res);

        }
        return res;
    }
}