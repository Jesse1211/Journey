package TwoArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        if (n == 0) {
            res.add(Arrays.asList(lower, upper));
            return res;
        }

        if (nums[0] > lower) {
            res.add(Arrays.asList(lower, nums[0] - 1));
            lower = nums[0];
        }

        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] > 1) {
                res.add(Arrays.asList(nums[i] + 1, nums[i + 1] - 1));
            }
        }

        if (nums[n - 1] < upper) {
            res.add(Arrays.asList(nums[n - 1] + 1, upper));
        }

        return res;
    }
}