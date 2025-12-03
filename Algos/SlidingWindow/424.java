package SlidingWindow;

class Solution {
    public int characterReplacement(String s, int k) {
        int[] dict = new int[26];

        int start = 0;
        int end = 0;
        int maxCount = 0;
        int res = 0;

        while (end < s.length()) {
            int cur = s.charAt(end) - 'A';
            dict[cur]++;

            maxCount = Math.max(maxCount, dict[cur]);

            while (end - start + 1 - maxCount > k) {
                dict[s.charAt(start) - 'A']--;
                start++;
            }

            res = Math.max(res, end - start + 1);
            end++;
        }

        return res;
    }
}