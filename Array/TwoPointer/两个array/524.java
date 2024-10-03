package TwoPointer.两个array;

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
        int wordIndex = 0;
        int sIndex = 0;
        while (sIndex < s.length()) {
            if (s.charAt(sIndex) == word.charAt(wordIndex)) {
                wordIndex++;

                if (wordIndex == word.length()) {
                    return wordIndex;
                }
            }
            sIndex++;
        }
        return 0;
    }
}