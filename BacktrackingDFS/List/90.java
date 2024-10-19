package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* continue的逻辑很好, 多看几遍
 * highlight:  有重复的数字, 为了避免res存在重复, 用dfs的时候, 要先sort, 然后在dfs的时候, 跳过重复的数字 
 * 其他和78.java一样
 */

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
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
            if (i > index && nums[i] == nums[i - 1]) {
                // 想象从第一个index出发, 如果两个index数字相同, 我们不能都用, 在此之前还要确定sorted
                continue;
            }
            cur.add(nums[i]);
            dfs(nums, res, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }
}