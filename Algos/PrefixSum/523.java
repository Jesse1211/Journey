package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
 * sub highlight: 这个难, 找前后两个数的关系
 * 1. 确定可以用一个forloop就可以完成
 * 2. 用一个map来记录 余数:index
 * 3. 判定逻辑: 
 *      衔接left edge的window: 如果余数为0，且index大于0，就返回true
 *      中间: 前后两个数的余数相同，且index的差大于1，就返回true
 *      如果都没有找到，就返回false
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // found from begining and length > 1
            if (sum % k == 0 && i > 0) {
                return true;
            }

            // found from middle and length > 1 (2 values added from begining with same diff
            // to k)
            if (map.containsKey(sum % k) && i - map.get(sum % k) > 1) {
                return true;
            }

            // not found
            if (!map.containsKey(sum % k)) {
                map.put(sum % k, i);
            }

        }
        return false;
    }
}
