package DataStructure;

import java.util.PriorityQueue;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < m; i++) {
            pq.offer(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            pq.offer(nums2[i]);
        }
        var index = 0;
        while (!pq.isEmpty()) {
            nums1[index++] = pq.poll();
        }
    }
}