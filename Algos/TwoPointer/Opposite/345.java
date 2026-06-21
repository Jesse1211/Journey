package Opposite;

class Solution {
    public String reverseVowels(String s) {
        char[] word = s.toCharArray();

        String vowels = "aeiouAEIOU";

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (vowels.indexOf(word[start]) != -1 && vowels.indexOf(word[end]) != -1) {
                char temp = word[start];
                word[start] = word[end];
                word[end] = temp;
                start++;
                end--;
                continue;
            }

            if (vowels.indexOf(word[start]) == -1) {
                start++;
            }

            if (vowels.indexOf(word[end]) == -1) {
                end--;
            }
        }
        String answer = new String(word);
        return answer;
    }
}