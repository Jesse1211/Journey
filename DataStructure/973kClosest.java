package DataStructure;

import java.util.PriorityQueue;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);

        for (var point : points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}