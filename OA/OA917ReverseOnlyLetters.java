package OA;

class Solution {
    static boolean isSpace(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            return false;
        }
        return true;
    }

    public String reverseOnlyLetters(String s) {
        char[] charList = s.toCharArray();
        int left = 0;
        int right = charList.length - 1;
        while (left < right) {
            if (isSpace(charList[left])) {
                left++;
            } else if (isSpace(charList[right])) {
                right--;
            } else {
                var temp = charList[left];
                charList[left] = charList[right];
                charList[right] = temp;
                left++;
                right--;
            }
        }
        return String.valueOf(charList);
    }
}