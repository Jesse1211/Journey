package SameDirection;

class Solution {
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        while (index < word.length()) {
            int oldIndex = index;
            while (index + 1 < word.length() && word.charAt(index) == word.charAt(index + 1)
                    && index - oldIndex + 1 < 9) {
                index++;
            }

            int dist = (index - oldIndex + 1);

            sb.append(dist).append(word.charAt(index));
            index++;
        }

        return sb.toString();
    }
}