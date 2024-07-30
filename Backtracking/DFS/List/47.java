package List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            visited.put(i, false); // INDEX
        }

        dfs(nums, res, new ArrayList<>(), visited);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur, HashMap<Integer, Boolean> visited) {
        if (nums.length == cur.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visited.get(i) == false) {
                if (set.contains(nums[i]))
                    continue;
                cur.add(nums[i]);
                visited.put(i, true);
                set.add(nums[i]);
                dfs(nums, res, cur, visited);
                cur.remove(cur.size() - 1);
                visited.put(i, false);
            }

        }
    }
}