package TwoPointer.反向;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long cur = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if (cur == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    } else if (cur < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

            }
        }

        List<List<Integer>> output = new ArrayList<>();
        output.addAll(res);
        return output;
    }
}
