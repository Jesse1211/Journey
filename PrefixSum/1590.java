package PrefixSum;

import java.util.HashMap;

class Solution {

    public int minSubarray(int[] nums, int p) {
        int n = nums.length;

        int totalSum = 0;
        for (int num : nums) {
            totalSum = (totalSum + num) % p;
        }

        if (totalSum == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>(); // value : index
        map.put(0, -1);

        int sum = 0;
        int res = n;

        for (int i = 0; i < n; ++i) {
            sum = (sum + nums[i]) % p;

            int needed = (sum - totalSum + p) % p; // +p是为了保证needed是positive number并且还是p的 mod

            if (map.containsKey(needed)) {
                res = Math.min(res, i - map.get(needed));
            }
            map.put(sum, i);
        }

        return res == n ? -1 : res;
    }
}