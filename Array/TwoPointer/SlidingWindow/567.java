package TwoPointer.SlidingWindow;

import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];

        if (s2.length() < s1.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++) {
            freqS1[s1.charAt(i) - 'a']++;
            freqS2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freqS1, freqS2)) {
            return true;
        }

        int slow = 0;
        for (int i = s1.length(); i < s2.length(); i++) {
            freqS2[s2.charAt(i) - 'a']++;
            freqS2[s2.charAt(slow) - 'a']--;

            slow++;
            if (Arrays.equals(freqS1, freqS2)) {
                return true;
            }

        }
        return false;
    }
}