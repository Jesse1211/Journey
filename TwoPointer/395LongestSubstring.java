package TwoPointer;

class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;
        if (k < 2)
            return s.length();
        return helper(s, 0, s.length(), k);
    }

    private int helper(String s, int left, int right, int k) {
        if (left >= right) {
            return 0;
        }

        int[] freqs = new int[26];
        for (int i = left; i < right; i++) {
            freqs[s.charAt(i) - 'a']++;
        }

        boolean flag = true;
        for (var freq : freqs) {
            if (freq > 0 && freq < k)
                flag = false;
        }

        if (flag == true) {
            return right - left;
        }

        int res = 0;
        int i = left;
        while (i < right) {
            if (freqs[s.charAt(i) - 'a'] < k) {
                res = Math.max(res, helper(s, left, i, k));
                left = i + 1;
            }
            i++;
        }
        res = Math.max(res, helper(s, left, right, k));
        return res;
    }
}