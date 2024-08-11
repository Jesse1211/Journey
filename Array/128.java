
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            set.add(i);
        }

        int res = 0;
        for (var num : nums) {
            int cur = 1;

            if (!set.contains(num + 1)) {
                int ls = num;
                while (set.contains(--ls)) {
                    cur++;
                }
            }
            res = Math.max(cur, res);
        }
        return res;
    }
}
