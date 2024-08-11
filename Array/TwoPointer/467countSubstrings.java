package TwoPointer;

class Solution {
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += numPalindromic(s, i, i);
            res += numPalindromic(s, i, i + 1);
        }
        return res;
    }

    private int numPalindromic(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right >= 0 && left < s.length() && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}