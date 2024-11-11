package 两个array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();

        int leftBound = lower;

        for (int i = 0; i < nums.length; i++) {

            // too small, jump to next number
            if (nums[i] < lower) {
                continue;
            }

            // there is a segment at left
            if (nums[i] > leftBound) {
                // segment from leftBound to cur - 1
                res.add(Arrays.asList(leftBound, nums[i] - 1));
            }

            leftBound = nums[i] + 1;

            if (leftBound > upper) {
                return res;
            }
        }

        if (leftBound <= upper) {
            res.add(Arrays.asList(leftBound, upper));
        }

        return res;
    }
}
