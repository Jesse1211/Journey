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
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<Integer> cur, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (index < i && candidates[i] == candidates[i - 1]) {
                // every recursive call in this level should not deduct same value -
                // candidates[i]
                continue;
            }
            cur.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}