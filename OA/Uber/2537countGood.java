import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countGood(int[] nums, int k) {
        int slow = 0;
        int fast = 0;
        long res = 0;
        long count = 0;

        Map<Integer, Integer> map = new HashMap<>();

        while (fast < nums.length) {
            map.put(nums[fast], map.getOrDefault(nums[fast], 0) + 1);
            count += map.get(nums[fast]) - 1;

            while (count >= k) {
                res += nums.length - fast;
                int left = map.get(nums[slow]);
                count -= left - 1;
                map.put(nums[slow], map.get(nums[slow]) - 1);
                if (map.get(nums[slow]) == 0)
                    map.remove(nums[slow]);
                slow++;
            }

            fast++;
        }
        return res;
    }
}