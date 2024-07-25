package TwoPointer.SlidingWindow;

class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int res = 0;
        int slow = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'A';
            // always move forward
            freq[cur]++;
            maxCount = Math.max(maxCount, freq[cur]);

            // if need to shrink
            if (i - slow + 1 - maxCount > k) {
                freq[s.charAt(slow) - 'A']--;
                slow++;
            }
            res = Math.max(res, i - slow + 1);
        }
        return res;
    }
}
