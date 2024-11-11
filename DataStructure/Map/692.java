package DataStructure.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    class Pair {
        String key;
        int value;

        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (var word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> a.value == b.value ? b.key.compareTo(a.key) : a.value - b.value);
        for (var ent : map.entrySet()) {
            pq.offer(new Pair(ent.getKey(), ent.getValue()));
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().key);
        }
        return res;
    }
}