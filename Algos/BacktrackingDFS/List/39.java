package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. 判定dfs的parameters: candidates, target, res, cur, index
*      当前candidates取决于candidates & index
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: 满足目标 target == 0
 * 3. dfs 如何更新: cur + candidate,
*      从index开始, 用forloop来遍历所有的candidates 
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, res, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, int index, List<Integer> cur) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                cur.add(candidates[i]);
                dfs(candidates, target - candidates[i], res, i, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}