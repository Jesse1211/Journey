package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * highlight:  有重复的数字, 为了避免res存在重复, 用dfs的时候, 要先sort, 然后在dfs的时候, 跳过重复的数字 
 * 其他和78.java一样
 */

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, 0, nums, new ArrayList<>());
        return res;
    }

    public void dfs(List<List<Integer>> res, int index, int[] nums, List<Integer> cur) {
        res.add(new ArrayList<>(cur));
        for (int i = index; i < nums.length; i++) {

            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            cur.add(nums[i]);
            dfs(res, i + 1, nums, cur);
            cur.remove(cur.size() - 1);
        }
    }
}