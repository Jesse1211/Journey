package PrefixSum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // sum: index
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // check if had sum - k before
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }

            // if had sum before, no need update index
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return res;
    }
}