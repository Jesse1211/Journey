
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> resList = new ArrayList<>();
        for (var num : nums2) {
            if (map.containsKey(num)) {
                resList.add(num);
                map.put(num, map.get(num) - 1);

                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;

    }
}