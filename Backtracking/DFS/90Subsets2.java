import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            if (i > index && nums[i] == nums[i - 1]) {continue;}
            cur.add(nums[i]);
            dfs(res, i + 1, nums, cur);
            cur.remove(cur.size() - 1);
        }
    }
}