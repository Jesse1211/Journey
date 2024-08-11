package TwoPointer.SlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] freqS = new int[26];
        int[] freqP = new int[26];
        List<Integer> res = new ArrayList<>();

        if (s.length() < p.length()) {
            return res;
        }

        for (int i = 0; i < p.length(); i++) {
            freqS[s.charAt(i) - 'a']++;
            freqP[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freqS, freqP)) {
            res.add(0);
        }

        int slow = 0;
        for (int i = p.length(); i < s.length(); i++) {
            freqS[s.charAt(i) - 'a']++;
            freqS[s.charAt(slow) - 'a']--;

            slow++;
            if (Arrays.equals(freqS, freqP)) {
                res.add(slow);
            }

        }
        return res;
    }
}