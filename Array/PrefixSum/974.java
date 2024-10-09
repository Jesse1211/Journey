package PrefixSum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> valToCount = new HashMap<>();
        valToCount.put(0, 1);

        int index = 0;
        int res = 0;
        int sum = 0;
        while (index < nums.length) {
            sum += nums[index];
            sum = ((sum % k) + k) % k; // Normalize to handle negative values

            res += valToCount.getOrDefault(sum, 0);

            valToCount.put(sum, valToCount.getOrDefault(sum, 0) + 1);
            index++;
        }
        return res;
    }
}