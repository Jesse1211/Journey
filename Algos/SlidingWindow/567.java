package SlidingWindow;

import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Fill the frequency map for s1
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freq1, freq2)) {
            return true;
        }

        int left = 0;
        int right = s1.length();
        while (right < s2.length()) {
            freq2[s2.charAt(right) - 'a']++;
            freq2[s2.charAt(left) - 'a']--;

            right++;
            left++;
            if (Arrays.equals(freq1, freq2)) {
                return true;
            }
        }

        return false;
    }
}
