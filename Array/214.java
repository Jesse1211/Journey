/*
 * highlight: 有刷题价值, 看left和right的变化. 还参考return的recursive写法
 */
class Solution {
    // KMP (Knuth-Morris-Pratt) algorithm
    public String shortestPalindrome(String s) {
        int length = s.length();
        if (length == 0) {
            return s;
        }

        // Find the longest palindromic prefix
        int left = 0;
        int right = length - 1;
        while (right >= 0) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
            }
            right--;
        }

        // If the whole string is a palindrome, return the original string
        if (left == length) {
            return s;
        }

        // Extract the suffix that is not part of the palindromic prefix
        String nonPalindromeSuffix = s.substring(left);
        StringBuilder reverseSuffix = new StringBuilder(
                nonPalindromeSuffix).reverse();

        // Form the shortest palindrome by prepending the reversed suffix
        return reverseSuffix
                .append(shortestPalindrome(s.substring(0, left)))
                .append(nonPalindromeSuffix)
                .toString();
    }
}
