package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.Arrays;
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
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visitedIndex = new HashSet<>();
        dfs(nums, new ArrayList<>(), res, visitedIndex);
        return res;
    }

    private void dfs(int[] nums, List<Integer> cur, List<List<Integer>> res, Set<Integer> visitedIndex) {
        if (nums.length == cur.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visitedIndex.contains(i)) {
                continue;
            }

            // 当前level不可以重复使用相同integer
            if (i > 0 && nums[i] == nums[i - 1] && !visitedIndex.contains(i - 1)) {
                continue;
            }
            visitedIndex.add(i);
            cur.add(nums[i]);
            dfs(nums, cur, res, visitedIndex);
            visitedIndex.remove(i);
            cur.remove(cur.size() - 1);
        }
    }
}