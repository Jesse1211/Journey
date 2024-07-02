// You are given an array of integers numbers. Return the difference between the
// sum of all even-positioned elements with values between -100 to 100
// (inclusive of both ends) and the sum of all odd-positioned elements with
// values that lie within the same range.

// Assume that array indices are 0-based.

// Note: You are not expected to provide the most optimal solution, but a
// solution with time complexity not worse than O(numbers.length^2) will fit
// within the execution time limit.

// Example 

// For numbers = [-3, 100, 50, 2, 51], the output should be solution(numbers) =
// 49.
// Explanation: The sum of even-positioned elements that fall within the range
// (-100, 100) is -3 + 50 + 51 = 98. The sum of odd-positioned elements that
// fall within the same range is 100 + 2 = 102. The difference is 98 - 102 = -4.

class Solution {
    public int evenOddDiff(int[] numbers) {
        int even = 0;
        int odd = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0 && numbers[i] >= -100 && numbers[i] <= 100) {
                even += numbers[i];
            } else if (i % 2 != 0 && numbers[i] >= -100 && numbers[i] <= 100) {
                odd += numbers[i];
            }
        }
        return even - odd;
    }
}