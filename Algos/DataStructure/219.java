package DataStructure;

import java.util.HashSet;
import java.util.Set;

/*
 * highlight: 用set存储窗口内的元素，如果set中已经有了，说明有重复元素
 */

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>(); // set stores everything within widnwo
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
        }
        return false;
    }
}