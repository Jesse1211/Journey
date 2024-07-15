// from 1 to n, each time choose only k numbers

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, n, k, new ArrayList<>(), 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, int n, int k, List<Integer> cur, int index) {

        if (k == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < n + 1; i++) {
            cur.add(i);
            dfs(res, n, k - 1, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}