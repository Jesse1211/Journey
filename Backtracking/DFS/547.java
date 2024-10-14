package Backtracking.DFS;

import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!set.contains(i)) {
                res++;
                dfs(isConnected, i, set);
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, int index, Set<Integer> set) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(index);
        set.add(index);
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[cur][i] == 1 && !set.contains(i)) {
                    q.offer(i);
                    set.add(i);
                }
            }
        }
    }
}