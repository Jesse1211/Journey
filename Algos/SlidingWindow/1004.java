package SlidingWindow;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, maxOnes = 0, zeroCount = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxOnes = Math.max(maxOnes, right - left + 1);
            right++;
        }
        return maxOnes;
    }
}

class Solution2 {
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