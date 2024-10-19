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

    public int countArrangement(int n) {
        Set<Integer> visited = new HashSet<>();
        return dfs(1, n, visited);
    }

    private int dfs(int index, int n, Set<Integer> visited) {
        if (index == n + 1) {
            return 1;
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (i % index == 0 || index % i == 0) {
                visited.add(i);
                res += dfs(index + 1, n, visited);
                visited.remove(i);
            }
        }
        return res;
    }
}
