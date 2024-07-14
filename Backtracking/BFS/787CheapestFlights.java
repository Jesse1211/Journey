package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new int[] { flight[1], flight[2] });
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { src, 0 });

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        while (!q.isEmpty() && 0 <= k) {
            int level = q.size();
            while (level-- > 0) {
                var cur = q.poll();
                int addr = cur[0];
                int cost = cur[1];

                var nexts = map.get(addr); // [to, price]
                if (nexts == null) {
                    continue;
                }
                for (var next : nexts) {
                    if (cost + next[1] >= minCost[next[0]])
                        continue;

                    minCost[next[0]] = cost + next[1];
                    q.offer(new int[] { next[0], next[1] + cost });

                }
            }
            k--;
        }
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}