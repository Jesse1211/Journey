package List;

import java.util.ArrayList;
import java.util.List;

/* 添加res没有先前条件, 所以dfs每次跑都会加到res中
 * 1. 判定dfs的parameters: nums, res, index, cur
 *    当前nums取决于index
 *    层层更新cur, 最终加到res中
 * 2. dfs 何时停止: index > nums.length
 * 3. dfs 如何更新: cur + nums[index],
 *    用forloop来遍历所有的nums, 因为有顺序, 所以每次recursive call的时候用的是for里面的i, 而不是index
 */

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int index, List<Integer> cur) {
        if (index > nums.length) {
            return;
        }

        res.add(new ArrayList<>(cur));

        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(nums, res, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }
}