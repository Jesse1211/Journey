package OA;

// https://www.fastprep.io/problems/calculate-ways
class Solution {
    public int calculateWays(int wordLen, int maxVowels) {
        int vowels = 5;
        int consonants = 21;
        int res = 0;
        for (int i = 1; i <= maxVowels; i++) {
            res += i * (int) Math.pow(consonants, wordLen - i - 1);
        }
    }
}