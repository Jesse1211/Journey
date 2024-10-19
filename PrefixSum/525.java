package PrefixSum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // sum: index
        int res = 0;
        int sum = 0;

        int index = 0;
        map.put(0, -1); // if valid subarray starts from index 0
        while (index < nums.length) {

            if (nums[index] == 0) {
                nums[index] = -1;
            }

            sum += nums[index];

            if (map.containsKey(sum)) {
                res = Math.max(res, index - map.get(sum));
            } else {
                map.put(sum, index);
            }

            index++;
        }
        return res;
    }
}