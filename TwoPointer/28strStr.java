package TwoPointer;

class Solution {
    public int strStr(String haystack, String needle) {

        if (haystack.length() < needle.length()) {
            return -1;
        }

        int left = 0;
        int right = 0;
        int index = 0;
        while (right < haystack.length() && index < needle.length()) {
            if (needle.charAt(index) == haystack.charAt(right)) {
                index++;
                right++;
            } else {
                left++;
                right = left;
                index = 0;
            }

            if ((right - left) == needle.length()) {
                return left;
            }

        }
        return -1;
    }
}