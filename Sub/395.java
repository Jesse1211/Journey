package Sub;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * highlight:
 *  和930比较像, 我们可以自主设定window size, 找出每个size能覆盖最长的substring
 */

class Solution {
    public int longestSubstring(String s, int k) {
        int n = findUniqueNums(s);
        int res = 0;
        int[] freq = new int[26];

        for (int uniqueNum = 1; uniqueNum <= n; uniqueNum++) {
            Arrays.fill(freq, 0);
            int slow = 0;
            int fast = 0;
            int curUniqueNumCount = 0;
            int amountOfK = 0;

            while (fast < s.length()) {
                // expand
                if (curUniqueNumCount <= uniqueNum) {
                    int index = s.charAt(fast) - 'a';
                    if (freq[index] == 0) {
                        curUniqueNumCount++;
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
                        curUniqueNumCount--;
                    }
                    slow++;

                }

                if (amountOfK == uniqueNum && curUniqueNumCount == amountOfK) {
                    res = Math.max(res, fast - slow);
                }
            }
        }
        return res;
    }

    private int findUniqueNums(String s) {
        Set<Character> set = new HashSet();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }
        return set.size();
    }
}