// Given two arrays of numbers, firstArray and secondArray, 
// return the length of the longest common prefix (LCP) between any pair of numbers from different arrays 
// or 0 if no common prefix exists.

// Note: A prefix of a number is a number formed by one or more of its digits, 
// starting from its highest-order digit. 
// For example, 123 is a prefix of the number 12345 and 2 is a prefix of the number 234. 
// A common prefix of two numbers is a number, which is a prefix of both. 
// For instance, the longest common prefix (LCP) of 5655359 and 56554 is 5655, and there is no common prefix of 234 and 567.

class Solution {
    public int longestPrefix(int[] firstArray, int[] secondArray) {
        int res = 0;

        for (int i = 0; i < firstArray.length; i++) {
            String firstCur = String.valueOf(firstArray[i]);
            for (int j = 0; j < secondArray.length; j++) {
                String secondCur = String.valueOf(secondArray[j]);
                int len = 0;
                while (len < firstCur.length() && len < secondCur.length()
                        && firstCur.charAt(len) == secondCur.charAt(len)) {
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] firstArray = { 5655359, 12345 };
        int[] secondArray = { 56554, 234 };
        System.out.println(s.longestPrefix(firstArray, secondArray)); // 4
    }
}