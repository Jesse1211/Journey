package TwoPointer.SlidingWindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestSubstring(String s, int k) {
        int n = findUniqueNums(s);
        int res = 0;
        int[] freq = new int[26];

        for (int uniqueNum = 1; uniqueNum <= n; uniqueNum++) {
            Arrays.fill(freq, 0);
            int slow = 0;
            int fast = 0;
            int curUniqueNum = 0;
            int amountOfK = 0;

            while (fast < s.length()) {
                // expand
                if (curUniqueNum <= uniqueNum) {
                    int index = s.charAt(fast) - 'a';
                    if (freq[index] == 0) {
                        curUniqueNum++;
                    }
                    freq[index]++;

                    if (freq[index] == k) {
                        amountOfK++;
                    }

                    fast++;
                }
                // shrink
                else {
                    int index = s.charAt(slow) - 'a';
                    if (freq[index] == k) {
                        amountOfK--;
                    }
                    freq[index]--;
                    if (freq[index] == 0) {
                        curUniqueNum--;
                    }
                    slow++;

                }

                if (amountOfK == uniqueNum && curUniqueNum == amountOfK) {
                    res = Math.max(res, fast - slow);
                }
            }
        }
        return res;
    }

    private int findUniqueNums(String s) {
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }
        return set.size();
    }
}