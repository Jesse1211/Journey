package TwoArrays;

import java.util.List;

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";

        for (String dict : dictionary) {
            if (deleteCount(dict, s)) {
                if (dict.length() > res.length()) {
                    res = dict;
                } else if (dict.length() == res.length()) {

                    if (res.compareTo(dict) > 0) {
                        res = dict;
                    }
                }
            }
        }
        return res;
    }

    private boolean deleteCount(String d, String s) {
        int left = 0;
        int right = 0;
        while (left < d.length() && right < s.length()) {
            if (d.charAt(left) == s.charAt(right)) {
                left++;
                right++;
            } else {
                right++;
            }

            if (left == d.length()) {
                return true;
            }
        }

        return false;
    }
}