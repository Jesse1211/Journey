package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length >= n) {
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        q.offer(0);
        visited.add(0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int child : map.get(cur)) {
                if (visited.contains(child)) {
                    continue;
                }
                visited.add(child);
                q.offer(child);
            }
        }

        return visited.size() == n;
    }
}