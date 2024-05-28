package OA;

// https://www.fastprep.io/problems/snowflake-count-ways-to-color-houses
class Solution {
    public int countWaysToColorHouses(int n) {
        if (n == 0) {
            return 0;
        }
        int length = n / 2;
        if (n % 2 == 1) {
            length++;
        }
        int[] dp = new int[length];
        int total = 9;
        dp[0] = 6;
        int res = 6;
        for (int i = 1; i < length; i++) {
            dp[i] = total - dp[i - 1];
            res *= dp[i];
        }

        return res;
    }
}