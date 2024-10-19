package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* highlight:  如果有重复的元素, 避免重复需要set, 但是recursive call前后要记得更新set
 *
 * 1.  判定dfs的parameters: nums, res, cur, visited
*      当前nums取决于cur是否包含
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: cur.size() == nums.length
 * 3. dfs 如何更新: cur + nums[i],
*      需要避免重复, 如果cur包含nums[i], 则跳过
*      用forloop来遍历所有的nums
 */

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, Set<Integer> visited, List<Integer> cur, List<List<Integer>> res) {
        if (nums.length == cur.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 当前level不可以重复使用相同integer
        Set<Integer> usedInt = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(i)) {
                continue;
            }
            if (usedInt.contains(nums[i])) {
                continue;
            }
            usedInt.add(nums[i]);
            visited.add(i);
            cur.add(nums[i]);
            dfs(nums, visited, cur, res);
            cur.remove(cur.size() - 1);
            visited.remove(i);
        }
    }
}