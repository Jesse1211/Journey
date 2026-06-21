package DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(freq[i]);
            }
        }

        int res = 0;

        while (!pq.isEmpty()) {
            int windowSize = n + 1;
            List<Integer> window = new ArrayList<>();
            int count = 0;
            while (windowSize-- > 0 && !pq.isEmpty()) {
                int cur = pq.poll();
                if (cur > 1) {
                    // pq.offer(cur - 1); // can not just offer due to PriorityQueue
                    window.add(cur - 1);
                }
                count++;
            }

            for (int w : window) {
                pq.offer(w);
            }
            res += (pq.isEmpty() ? count : n + 1);
            // if pq is empty: this is the last window
        }
        return res;
    }
}