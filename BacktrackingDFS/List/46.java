package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* : 如果所有element都是unique, 避免重复不需要set, 检查是否contain就好了
 * 1.  判定dfs的parameters: nums, res, cur (因为这次是无序, 所以不需要index)
*      当前nums取决于cur是否包含
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: cur.size() == nums.length
 * 3. dfs 如何更新: cur + nums[i],
*      需要避免重复, 如果cur包含nums[i], 则跳过 
*      用forloop来遍历所有的nums
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, Set<Integer> visited, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }
            visited.add(num);
            cur.add(num);
            dfs(nums, visited, cur, res);
            visited.remove(num);
            cur.remove(cur.size() - 1);
        }
    }
}