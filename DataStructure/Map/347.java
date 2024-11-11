package DataStructure.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        List<List<Integer>> bucket = new ArrayList<>(); // ith: [the integers has i frequency]
        Map<Integer, Integer> map = new HashMap<>(); // num : freq

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            bucket.add(new ArrayList<>());
        }

        for (var entry : map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            bucket.get(freq - 1).add(num); // due to index
        }

        int[] res = new int[k];
        int bucketIndex = bucket.size() - 1;
        int resIndex = 0;
        while (k > 0) {
            if (bucket.get(bucketIndex).size() > 0) {
                for (int num : bucket.get(bucketIndex)) {
                    res[resIndex] = num;
                    resIndex++;
                    k--;
                }
            }
            bucketIndex--;
        }
        return res;
    }
}