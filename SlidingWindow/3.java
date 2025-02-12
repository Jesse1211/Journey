package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;

        Set<Character> set = new HashSet<>();
        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            while (set.contains(s.charAt(fast))) {
                set.remove(s.charAt(slow++));
            }

            set.add(s.charAt(fast));
            fast++;

            res = Math.max(fast - slow, res);
        }

        return res;
    }
}
