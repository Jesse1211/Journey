package List;

import java.util.HashSet;
import java.util.Set;

/*
 * 1. 判定dfs的parameters: index, n, visited
 *      当前进度取决于index和n
 *      visited防重
 * 2. dfs 何时停止: index == n + 1
 * 3. dfs 如何更新: dfs(index + 1, n, visited);
 */

class Solution {
    int res;

    public int countArrangement(int n) {
        res = 0;
        Set<Integer> visited = new HashSet<>();
        dfs(1, n, visited);
        return res;
    }

    private void dfs(int index, int n, Set<Integer> visited) {
        if (index == n + 1) {
            res++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (i % index == 0 || index % i == 0) {
                visited.add(i);
                dfs(index + 1, n, visited);
                visited.remove(i);
            }
        }
    }
}
