package List;

import java.util.ArrayList;
import java.util.List;

/* highlight: 如果所有element都是unique, 避免重复不需要set, 检查是否contain就好了
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
        dfs(nums, res, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (cur.contains(nums[i])) {
                continue;
            }
            cur.add(nums[i]);
            dfs(nums, res, cur);
            cur.remove(cur.size() - 1);
        }
    }
}