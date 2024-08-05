package List;

import java.util.ArrayList;
import java.util.List;

/* 
 * 1. 判定dfs的parameters: k, n, res, cur, index
 *      当前候选数取决于index
 *      当前是否满足条件取决于k, n
 * 2. dfs 何时停止: k == 0 && n == 0
 * 3. dfs 如何更新: cur.add(i) 但是i是当前cur的最大值 (防重)
 */

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, k, n, new ArrayList<>(), 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, int k, int n, List<Integer> cur, int index) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        if (n < 0) {
            return;
        }

        for (int i = index; i <= 9; i++) {
            cur.add(i);
            dfs(res, k - 1, n - i, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}