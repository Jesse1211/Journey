package TwoPointer;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int longestOnes(int[] nums, int k) {
        Queue<Integer> q = new ArrayDeque<>();

        int res = 0;
        int left = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                q.add(i);
            }

            if (q.size() > k) {
                left = q.poll();
            }

            res = Math.max(res, i - left);

        }
        return res;
    }
}