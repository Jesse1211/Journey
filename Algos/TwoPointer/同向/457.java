package 同向;

import java.util.HashSet;
import java.util.Set;

/*
 * highlight: 快慢指针 - 挺繁琐, 但是思路很清晰, 除了题目不太好理解
 */

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int index = 0;

        while (index < nums.length) {
            if (!set.contains(index)) {
                // suppose get into one cycle
                int slow = index;
                int fast = index;
                // 找到一个方向, 之后如果方向不同, 说明不是一个cycle
                boolean isPositive = nums[index] >= 0;
                while (true) {
                    // slow moves once, fast moves twice until merge
                    slow = updateIndex(nums, slow, isPositive);
                    if (slow == -1) {
                        // 方向不同
                        break;
                    }

                    fast = updateIndex(nums, fast, isPositive);
                    if (fast == -1) {
                        break;
                    }

                    fast = updateIndex(nums, fast, isPositive);
                    if (fast == -1) {
                        break;
                    }

                    if (slow == fast) {
                        return true;
                    }
                    set.add(slow);
                    set.add(fast);
                }

            }
            index++;
        }
        return false;
    }

    private int updateIndex(int[] nums, int index, boolean isPositive) {
        // 方向不能改变!!
        boolean direction = nums[index] >= 0;
        if (direction != isPositive) {
            return -1;
        }
        int nextIndex = (nums[index] + index) % nums.length;
        if (nextIndex < 0) {
            nextIndex += nums.length;
        }
        if (nextIndex == index) { // cycle
            return -1;
        }
        return nextIndex;
    }
}