package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 1. 判定dfs的parameters: candidates, target, res, cur, index
*      当前candidates取决于index
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: 没有candidates, target < 0
 * 3. dfs 如何更新: cur + candidate,
*      需要避免重复, 如果当前candidate和上一个相同, 则跳过, 因为上一个已经把所有的情况都考虑了
*      用forloop来遍历所有的candidates
 * 
 */

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur, int index) {

        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            cur.add(candidates[i]);
            dfs(candidates, target - candidates[i], res, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}