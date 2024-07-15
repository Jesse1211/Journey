import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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