package DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (var ent : map.entrySet()) {
            pq.add(new int[] { ent.getKey(), ent.getValue() });
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] res = new int[k];
        var index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll()[0];
        }
        return res;
    }
}