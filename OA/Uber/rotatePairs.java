
// int array, count the number of distinct pairs where 0 <= i < j < length of
// array and nums[i] == nums[j] where you're allowed to swap two positions of
// nums[j] to make it equal to nums[i].
// Example:‍‍‍‍‌‌‌‍‍‍‍‍‌‌‌‍‍‍‍
// [1, 23, 156, 4738, 321, 72992, 231, 651, 32]
// Result = 3: [23, 32] [156, 651] [321, 231]
// note: [230, 032] is not a pair
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int rotatePairs(int[] nums) {
        HashMap<String, Integer> freqMap = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            char[] digits = String.valueOf(num).toCharArray();
            Arrays.sort(digits);
            String sortedNum = new String(digits);

            freqMap.put(sortedNum, freqMap.getOrDefault(sortedNum, 0) + 1);
        }

        for (int freq : freqMap.values()) {
            if (freq > 1) {
                count += (freq * (freq - 1)) / 2;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 1, 23, 156, 4738, 321, 72992, 231, 651, 32 };
        System.out.println(solution.rotatePairs(nums)); // Output: 3
    }
}
