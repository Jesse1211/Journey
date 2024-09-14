package Sub;

/*
 * highlight: Sliding Window难难难
 */

class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        int[] freq = new int[26];
        int cur = 0;
        int left = 0;
        int right = 0;
        int maxFreq = 0;

        while (right < s.length()) {
            cur = s.charAt(right) - 'A';
            freq[cur]++;
            maxFreq = Math.max(maxFreq, freq[cur]); // window size只能越来越大, 不能小
            while (right - left + 1 - maxFreq > k) {
                // shrink
                int removed = s.charAt(left) - 'A';
                freq[removed]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }
}