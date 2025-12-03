package BFS.FromCenter;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [weight, point index]
        Set<Integer> visited = new HashSet<>();
        pq.offer(new int[] { 0, 0 });

        while (visited.size() < points.length) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int index = cur[1];
            if (visited.contains(index)) {
                continue;
            }
            visited.add(index);
            res += cost;

            for (int i = 0; i < points.length; i++) {

                if (visited.contains(i)) {
                    continue;
                }

                // visited.add(i); // 这里不能加, 因为i不一定之后会被poll出来
                int dist = findDist(points[index][0], points[index][1], points[i][0], points[i][1]);
                pq.offer(new int[] { dist, i });
            }
        }

        return res;
    }

    private int findDist(int xi, int yi, int xj, int yj) {
        return Math.abs(xi - xj) + Math.abs(yi - yj);
    }
}