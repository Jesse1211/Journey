package DataStructure.Map;

import java.util.PriorityQueue;

class Solution {
    // Time: O(nlogn), Space: O(n)
    // 这个太慢了
    public int longestSubarray(int[] nums, int limit) {
        int slow = 0;
        int fast = 0;
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);
        int maxLen = 0;
        while (fast < nums.length) {
            maxPq.offer(nums[fast]);
            minPq.offer(nums[fast]);

            var diff = maxPq.peek() - minPq.peek();
            if (diff > limit) {
                maxPq.remove(nums[slow]);
                minPq.remove(nums[slow]);
                slow += 1;
                fast += 1;
            } else {
                maxLen = Math.max(maxLen, fast - slow + 1);
                fast += 1;
            }
        }
        return maxLen;
    }
}