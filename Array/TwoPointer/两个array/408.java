package TwoPointer.两个array;

// meta
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0;
        int abbrIndex = 0;
        while (wordIndex < word.length() && abbrIndex < abbr.length()) {
            Character w = word.charAt(wordIndex);
            Character a = abbr.charAt(abbrIndex);

            if (w == a) {
                wordIndex++;
                abbrIndex++;
                continue;
            }

            if (!Character.isDigit(a) || a == '0') {
                return false;
            }

            int len = 0;
            while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
                len = len * 10 + (abbr.charAt(abbrIndex) - '0');
                abbrIndex++;
            }
            wordIndex += len;
        }
        return wordIndex == word.length() && abbrIndex == abbr.length();
    }
}