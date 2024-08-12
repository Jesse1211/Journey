package TwoPointer.同向;

class Solution {
    public int strStr(String haystack, String needle) {
        int slow = 0;
        int fast = 0;
        int index = 0;

        while (fast < haystack.length() && index < needle.length()) {
            if (haystack.charAt(fast) == needle.charAt(index)) {
                fast++;
                index++;
            } else {
                slow++;
                fast = slow;
                index = 0;
                continue;
            }

            if (index == needle.length()) {
                return slow;
            }
        }

        return -1;
    }
}