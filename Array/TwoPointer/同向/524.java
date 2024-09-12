package TwoPointer.同向;

import java.util.List;

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {

        String res = "";

        for (String word : dictionary) {
            int length = checkIfWordPresentInString(s, word);

            if (res.length() < length || (res.length() == length && res.compareTo(word) > 0)) {
                res = word;
            }
        }

        return res;
    }

    public int checkIfWordPresentInString(String s, String word) {
        int index = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) == word.charAt(index)) {
                index++;

                if (index == word.length()) {
                    return index;
                }
            }
        }
        return 0;
    }
}