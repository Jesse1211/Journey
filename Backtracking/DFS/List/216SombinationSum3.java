package List;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, res, new ArrayList<>(), 1);
        return res;
    }

    private void dfs(int level, int target, List<List<Integer>> res, List<Integer> cur, int index) {

        if (target == 0) {
            if (cur.size() == level) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = index; i <= 9; i++) {
            cur.add(i);
            dfs(level, target - i, res, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}