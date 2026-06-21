package Opposite;

/**
 * highlight: 对于删除一个, 或者多个的情况, 要逆向思考
 */

class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }

        }
        return true;
    }

    public boolean isPalindrome(String s, int i, int j) {
        while (i >= 0 && j >= 0 && i < s.length() && j < s.length() && i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }

        }
        return true;
    }
}