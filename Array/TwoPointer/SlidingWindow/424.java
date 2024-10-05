package TwoPointer.SlidingWindow;

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int[] window = new int[26];
        int maxFreq = 0;
        int res = 0;
        while (right < s.length()) {

            int cur = s.charAt(right) - 'A';
            window[cur]++;
            maxFreq = Math.max(maxFreq, window[cur]);

            while (right - left + 1 > maxFreq + k) {
                window[s.charAt(left) - 'A']--;
                left++;
            }

            res = Math.max(res, right - left + 1); // 这里可以用maxFreq但是要考虑到edge case
            right++;
        }
        return res;
    }
}
