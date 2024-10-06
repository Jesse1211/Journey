package Sub;

class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = longestLength(s, i, i);
            String even = longestLength(s, i, i + 1);

            if (odd.length() > res.length()) {
                res = odd;
            }
            if (even.length() > res.length()) {
                res = even;
            }
        }
        return res;
    }

    public String longestLength(String s, int i, int j) {
        while (i >= 0 && j >= 0 && i < s.length() && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i + 1, j);
    }
}