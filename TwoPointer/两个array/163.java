package 两个array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();

        int left = lower;

        for (int i : nums) {
            if (i < left) {
                continue;
            }

            if (i > left) {
                res.add(Arrays.asList(left, i - 1));
            }

            left = i + 1;

            if (i > upper) {
                return res;
            }
        }

        if (left <= upper) {
            res.add(Arrays.asList(left, upper));
        }

        return res;
    }
}