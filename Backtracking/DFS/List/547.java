package List;

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

    private void dfs(int[][] isConnected, int cur, Set<Integer> set) {
        if (set.contains(cur)) {
            return;
        }

        set.add(cur);

        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[cur][i] == 1) {
                dfs(isConnected, i, set);
            }
        }
    }
}