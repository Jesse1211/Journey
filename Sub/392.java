package Sub;

class Solution {
    public boolean isSubsequence(String t, String s) {
        int index = 0;
        int i = 0;
        while (i < s.length() && index < t.length()) {
            if (s.charAt(i) == t.charAt(index)) {
                index++;
            }
            i++;
        }
        return index == t.length();
    }
}