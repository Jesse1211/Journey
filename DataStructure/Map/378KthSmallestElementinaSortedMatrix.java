package DataStructure.Heap;

import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                pq.offer(matrix[r][c]);
                if (pq.size() > k)
                    pq.poll();
            }
        }
        return pq.poll();
    }
}