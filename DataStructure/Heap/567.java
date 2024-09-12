package DataStructure.Heap;

/*
 * highlight: 这个难
 */

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] map = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']++;
        }

        int start = 0;
        int count = s1.length();

        for (int i = 0; i < s2.length(); i++) {
            int cur = s2.charAt(i) - 'a';
            if (map[cur] > 0) {
                count--;
            }
            map[cur]--;

            if (count == 0) {
                return true;
            }

            if (i - start + 1 == s1.length()) {
                if (map[s2.charAt(start) - 'a'] >= 0) {
                    count++;
                }
                map[s2.charAt(start) - 'a']++;
                start++;
            }
        }

        return false;
    }
}