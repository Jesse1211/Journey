/*
 * highlight: 考了很多structure的题目，这个题目是一个很好的例子，用array来存储int和对应的roman
 */

class Solution {
    public String intToRoman(int num) {
        String roman = "";
        int[] storeInt = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] storeRoman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        for (int i = 0; i < storeInt.length; i++) {
            while (num >= storeInt[i]) {
                roman += storeRoman[i];
                num -= storeInt[i];
            }
        }

        return roman;
    }
}